package openBankingApi.test.account.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import openBankingApi.test.account.dto.AccountBalanceDto;
import openBankingApi.test.account.dto.AccountInfoListDto;
import openBankingApi.test.basic.exception.BusinessException;
import openBankingApi.test.basic.exception.ExMessage;
import openBankingApi.test.oauth.entity.OauthToken;
import openBankingApi.test.oauth.repository.OauthTokenRepository;
import openBankingApi.test.user.entity.Member;
import openBankingApi.test.user.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

	private final OauthTokenRepository tokenRepository;

	@Value("${open_api.agency_code}")
	private String agencyCode;

	@Transactional(readOnly = true)
	public AccountBalanceDto getAccountBalance(
			String userId, String fintechUseNum
	) {
		OauthToken oauthToken = tokenRepository.findByUserId(userId)
				.orElseThrow(() -> new BusinessException(ExMessage.NOT_FOUND_ERROR));

		String accessToken = oauthToken.getAccessToken();
		String randNum = String.valueOf(
				new Random(System.currentTimeMillis()).nextInt(1000000000 - 100000001) + 100000000
		);
		String bankTranId = agencyCode + "U" + randNum;
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
		String tranDtime = df.format(new Date());

		ResponseEntity<String> response = getStringResponse(accessToken, fintechUseNum, bankTranId, tranDtime);
		return new Gson().fromJson(response.getBody(), AccountBalanceDto.class);
	}

	private ResponseEntity<String> getStringResponse(
			String accessToken, String fintechUseNum, String bankTranId, String tranDtime
	) {
		RestTemplate rest = new RestTemplate();

		String url = "https://testapi.openbanking.or.kr/v2.0/account/balance/fin_num?" +
				"bank_tran_id={bank_tran_id}" +
				"&fintech_use_num={fintech_use_num}" +
				"&tran_dtime={tran_dtime}";

		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(accessToken);
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		return rest.exchange(
				url,
				HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class,
				bankTranId,
				String.valueOf(fintechUseNum),
				tranDtime
		);
	}

	@Transactional(readOnly = true)
	public AccountInfoListDto getAccountList(String userId, String includeCancelYn, String sortOrder) {
		OauthToken oauthToken = tokenRepository.findByUserId(userId)
				.orElseThrow(() -> new BusinessException(ExMessage.NOT_FOUND_ERROR));

		ResponseEntity<String> response = getStringResponseEntity(includeCancelYn, sortOrder, oauthToken);

		return new Gson().fromJson(response.getBody(), AccountInfoListDto.class);
	}

	private ResponseEntity<String> getStringResponseEntity(String includeCancelYn, String sortOrder, OauthToken oauthToken) {
		RestTemplate rest = new RestTemplate();

		String url = "https://testapi.openbanking.or.kr/v2.0/account/list" +
				"?user_seq_no={user_seq_no}" +
				"&include_cancel={include_cancel}" +
				"&sort_order={sort_order}";

		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(oauthToken.getAccessToken());

		HttpEntity entity = new HttpEntity(headers);

		return rest.exchange(
				url,
				HttpMethod.GET,
				entity,
				String.class,
				oauthToken.getUserSeqNo(),
				includeCancelYn,
				sortOrder
		);
	}
}
