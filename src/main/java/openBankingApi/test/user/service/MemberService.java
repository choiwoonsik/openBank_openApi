package openBankingApi.test.user.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import openBankingApi.test.basic.exception.BusinessException;
import openBankingApi.test.basic.exception.ExMessage;
import openBankingApi.test.basic.response.SingleResult;
import openBankingApi.test.oauth.entity.OauthToken;
import openBankingApi.test.oauth.repository.OauthTokenRepository;
import openBankingApi.test.restTemplate.RestTemplateService;
import openBankingApi.test.user.dto.MemberDto;
import openBankingApi.test.user.dto.UserInfoAndAccountList;
import openBankingApi.test.user.entity.Member;
import openBankingApi.test.user.repository.MemberRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

	private final OauthTokenRepository tokenRepository;
	private final MemberRepository memberRepository;

	public MemberDto findByUserSeqNo(Long userSeqNo) {
		Member member = memberRepository.findByUserSeqNo(userSeqNo)
				.orElseThrow(() -> new BusinessException(ExMessage.NOT_FOUND_ERROR));
		return member.toDto();
	}

	@Transactional
	public void saveUser(MemberDto reqDto) {
		if (!memberRepository.existsByUserSeqNo(reqDto.getUserSeqNo())) {
			memberRepository.save(reqDto.toEntity());
		}
	}

	@Transactional(readOnly = true)
	public UserInfoAndAccountList getUserInfo(String userName, String userMobile) {
		OauthToken oauthToken = tokenRepository.findByUserNameAndUserMobile(userName, userMobile)
				.orElseThrow(() -> new BusinessException(ExMessage.UNDEFINED_ERROR));

		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(oauthToken.getAccessToken());

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> result = restTemplate.exchange(
				"https://testapi.openbanking.or.kr/v2.0/user/me?user_seq_no={seq}",
				HttpMethod.GET,
				new HttpEntity(headers),
				String.class,
				oauthToken.getUserSeqNo()
		);

		return new Gson().fromJson(result.getBody(), UserInfoAndAccountList.class);
	}
}