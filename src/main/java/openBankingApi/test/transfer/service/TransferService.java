package openBankingApi.test.transfer.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import openBankingApi.test.basic.exception.BusinessException;
import openBankingApi.test.basic.exception.ExMessage;
import openBankingApi.test.oauth.entity.OauthToken;
import openBankingApi.test.oauth.repository.OauthTokenRepository;
import openBankingApi.test.transfer.dto.TransferWdFinNumReq;
import openBankingApi.test.transfer.dto.TransferWdFinNumRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TransferService {

	private final OauthTokenRepository tokenRepository;

	@Value("${open_api.agency_code}")
	private String agencyCode;

	public TransferWdFinNumRes transferWithdrawByFinNum(TransferWdFinNumReq req) {
		String userId = req.getUserId();

		OauthToken oauthToken = tokenRepository.findByUserId(userId)
				.orElseThrow(() -> new BusinessException(ExMessage.NOT_FOUND_ERROR));

		String accessToken = oauthToken.getAccessToken();
		String randNum = String.valueOf(new Random(System.currentTimeMillis()).nextInt(1000000000 - 100000001) + 100000000);
		String bankTranId = agencyCode + "U" + randNum;

		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
		DateFormat userDf = new SimpleDateFormat("ddHHmmss", Locale.KOREA);
		String tranDtime = df.format(new Date());

		req.setBank_tran_id(bankTranId);
		req.setCntr_account_type("N");
		req.setTran_dtime(tranDtime);

		StringBuilder clientTime = new StringBuilder(userDf.format(new Date()));
		int len = userId.length();
		if (len < 12) {
			while (len < 12) {
				clientTime.append("0");
				len++;
			}
		} else {
			userId = userId.substring(12 - len);
		}
		String reqClientNum = userId.toUpperCase(Locale.ROOT) + clientTime;
		req.setReq_client_num(reqClientNum);
		req.setTransfer_purpose("TR");
		req.setReq_client_name(req.getUserName());

		/*
		최종수취고객 정보
		 */
		// req.setRecv_client_bank_code();
		// req.setRecv_client_name();
		// req.setRecv_client_account_num();

		ResponseEntity<String> response = getStringResponse(accessToken, req);
		return new Gson().fromJson(response.getBody(), TransferWdFinNumRes.class);
	}

	private ResponseEntity<String> getStringResponse(
			String accessToken, TransferWdFinNumReq req
	) {
		RestTemplate rest = new RestTemplate();

		String url = "https://testapi.openbanking.or.kr/v2.0/transfer/withdraw/fin_num";

		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(accessToken);
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity entity = new HttpEntity(req, headers);

		return rest.postForEntity(url, entity, String.class);
	}
}
