package openBankingApi.test.oauth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import openBankingApi.test.basic.exception.BusinessException;
import openBankingApi.test.basic.exception.ExMessage;
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

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

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

	public AuthorizeReqDto createAuthorizeCode(Map<String, String> map) {
		return AuthorizeReqDto.builder()
				.code(map.get("code"))
				.clientInfo(map.get("client_info"))
				.scope(map.get("scope"))
				.state(map.get("state"))
				.build();
	}

	@Transactional
	public OauthToken saveAuthorizeToken(
			String code, String scope, String client_info, String state
	) {
		if (!client_info.equals("woonsik") || !state.equals("12345678901234567890123456789012")) {
			throw new BusinessException("사전 정보 오류");
		}

		RestTemplate restTemplate = new RestTemplate();
		String openUrl = "https://testapi.openbanking.or.kr/oauth/2.0/token";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("code", code);
		params.add("client_id", client_id);
		params.add("client_secret", client_secret);
		params.add("redirect_uri", redirect_uri);
		params.add("grant_type", "authorization_code");


		HttpEntity formEntity = new HttpEntity<>(params, headers);

		ResponseEntity<OauthTokenRes> response
				= restTemplate.postForEntity(openUrl, formEntity, OauthTokenRes.class);

		log.info("인가 코드 : " + code);
		log.info("응답 코드 : " + response.getStatusCode());

		if (response.getStatusCode() == HttpStatus.OK
				&& response.getBody() != null
				&& response.getBody().getAccess_token() != null)
		{
			log.info("res > " + response.getBody());
			OauthToken oauthToken = response.getBody().toEntity();
			oauthToken.setRegDate(LocalDateTime.now());
			saveOauthToken(oauthToken);
			return oauthToken;
		}
		throw new BusinessException("응답 토큰 비어있음");
	}

	@Transactional
	public void saveOauthToken(OauthToken token) {
		try {
			Optional<OauthToken> userOpt = oauthTokenRepository.findByUserSeqNo(token.getUserSeqNo());
			if (userOpt.isPresent()) {
				OauthToken oauthToken = userOpt.get();
				oauthToken.setAccessToken(token.getAccessToken());
				oauthToken.setRefreshToken(token.getRefreshToken());
				oauthToken.setExpiresIn(token.getExpiresIn());
				oauthToken.setRegDate(LocalDateTime.now());
				oauthTokenRepository.save(oauthToken);
			} else {
				oauthTokenRepository.save(token);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}
}
