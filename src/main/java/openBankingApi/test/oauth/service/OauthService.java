package openBankingApi.test.oauth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import openBankingApi.test.oauth.dto.AuthorizeReqDto;
import openBankingApi.test.oauth.dto.OauthTokenRes;
import openBankingApi.test.oauth.entity.OauthToken;
import openBankingApi.test.oauth.repository.OauthTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OauthService {

	private final OauthTokenRepository oauthTokenRepository;

	@Value("${oauth.client_id}")
	private String client_id;

	@Value("${oauth.client_secret}")
	private String client_secret;

	@Value("${oauth.redirect_uri}")
	private String redirect_uri;

	public AuthorizeReqDto createAuthoCode(Map<String, String> map) {
		return AuthorizeReqDto.builder()
				.code(map.get("code"))
				.client_info(map.get("client_info"))
				.scope(map.get("scope"))
				.state(map.get("state"))
				.build();
	}

	public Model saveAuthorizeToken(
			Model model, String code, String scope, String client_info, String state
	) {
		System.out.println("인가 코드 : " + code);

		if (!client_info.equals("woonsik") || !state.equals("12345678901234567890123456789012")) {
			throw new RuntimeException("Error");
		}

		RestTemplate restTemplate = new RestTemplate();
		OauthTokenRes dto = new OauthTokenRes();
		String openUrl = "https://testapi.openbanking.or.kr/oauth/2.0/token";

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("code", code);
		params.add("client_id", client_id);
		params.add("client_secret", client_secret);
		params.add("redirect_uri", redirect_uri);
		params.add("grant_type", "authorization_code");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity formEntity = new HttpEntity<>(params, headers);

		ResponseEntity<OauthTokenRes> response
				= restTemplate.postForEntity(openUrl, formEntity, OauthTokenRes.class);

		log.info("Response Code : " + response.getStatusCode());

		if (response.getStatusCode() == HttpStatus.OK
				&& response.getBody() != null
				&& response.getBody().getUser_seq_no() != null) {
			dto = response.getBody();
			log.info("body : "+response.getBody());
			model.addAttribute("tokenDto", dto);
			saveOauthToken(dto.toEntity());
		}
		return model;
	}

	@Transactional
	public void saveOauthToken(OauthToken token) {
		try {
			oauthTokenRepository.save(token);
		} catch (Exception e) {
			throw new RuntimeException("저장 실패");
		}
	}
}
