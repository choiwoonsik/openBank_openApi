package openBankingApi.test.oauth.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import openBankingApi.test.basic.exception.BusinessException;
import openBankingApi.test.basic.exception.ExMessage;
import openBankingApi.test.basic.response.ResponseService;
import openBankingApi.test.basic.response.SingleResult;
import openBankingApi.test.oauth.entity.OauthToken;
import openBankingApi.test.oauth.service.OauthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OauthApi {

	private final OauthService oauthService;
	private final ResponseService responseService;

	@GetMapping("/oauth/authorizeCode")
	public String requestAuthorizeCode() {
		String url = "https://testapi.openbanking.or.kr/oauth/2.0/authorize?response_type=code&client_id=901765e4-1f64-448b-9961-ca7e2c1bb9b3&redirect_uri=http://localhost:9090/oauth/callback.html&scope=login inquiry transfer&client_info=woonsik&state=12345678901234567890123456789012&auth_type=0&lang=kor&cellphone_cert_yn=Y&authorized_cert_yn=N&account_hold_auth_yn=N";
		return "redirect:" + url;
	}

	@ResponseBody
	@GetMapping("/oauth/callback.html")
	public SingleResult<OauthToken> requestToken(
			@RequestParam(name = "code") String code,
			@RequestParam(name = "scope") String scope,
			@RequestParam(name = "client_info") String clientInfo,
			@RequestParam(name = "state") String state
	) {
		log.info(code + ", " + scope + ", " + clientInfo + ", " + state);
		try {
			OauthToken oauthToken = oauthService.saveAuthorizeToken(code, scope, clientInfo, state);
			return responseService.getSingleResult(oauthToken);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(ExMessage.UNDEFINED_ERROR);
		}
	}
}

