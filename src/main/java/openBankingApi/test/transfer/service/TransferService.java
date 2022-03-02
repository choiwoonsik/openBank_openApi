package openBankingApi.test.transfer.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import openBankingApi.test.basic.exception.BusinessException;
import openBankingApi.test.basic.exception.ExMessage;
import openBankingApi.test.oauth.entity.OauthToken;
import openBankingApi.test.oauth.repository.OauthTokenRepository;
import openBankingApi.test.transfer.dto.TransferWithdrawFinNumReq;
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

	public void transferWithdrawByFinNum(TransferWithdrawFinNumReq req) {
		String clientName = req.getClientName();
		String clientMobile = req.getClientMobile();

		OauthToken oauthToken = tokenRepository.findByUserNameAndUserMobile(clientName, clientMobile)
				.orElseThrow(() -> new BusinessException(ExMessage.NOT_FOUND_ERROR));

		String accessToken = oauthToken.getAccessToken();
		String randNum = String.valueOf(new Random(System.currentTimeMillis()).nextInt(1000000000));
		String bankTranId = agencyCode + "U" + randNum;

		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
		String tranDtime = df.format(new Date());

		req.setBank_tran_id(bankTranId);
		req.setCntr_account_type("N");
		req.setTran_dtime(tranDtime);
		req.setClientName(oauthToken.getUserName());
		req.setReq_client_num(clientName+tranDtime);
		req.setTransfer_purpose("TR");

		ResponseEntity<String> response = getStringResponse(accessToken, req);
		System.out.println(response);
//		new Gson().fromJson(response.getBody(), )
	}

	private ResponseEntity<String> getStringResponse(
			String accessToken, TransferWithdrawFinNumReq req
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
