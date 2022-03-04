package openBankingApi.test.oauth.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import openBankingApi.test.basic.exception.BusinessException;
import openBankingApi.test.basic.exception.ExMessage;
import openBankingApi.test.basic.response.ResponseService;
import openBankingApi.test.basic.response.SingleResult;
import openBankingApi.test.oauth.entity.OauthToken;
import openBankingApi.test.oauth.service.OauthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/oauth")
@Slf4j
@Controller
@RequiredArgsConstructor
public class OauthApi {

	private final OauthService oauthService;
	private final ResponseService responseService;

	@Value("${open_api.state}")
	String state;

	@GetMapping("/authorizeCode")
	public String requestAuthorizeCode(
			@RequestParam String userId,
			@RequestParam String userName,
			@RequestParam String userMobile
	) {
		String url = "https://testapi.openbanking.or.kr/oauth/2.0/authorize" +
				"?response_type=code" +
				"&client_id=901765e4-1f64-448b-9961-ca7e2c1bb9b3" +
				"&redirect_uri=http://localhost:9090/oauth/callback.html" +
				"&scope=login inquiry transfer" +
				"&client_info=" + userId + "-" + userName + "-" + userMobile +
				"&state=" + state +
				"&auth_type=0" +
				"&lang=kor" +
				"&cellphone_cert_yn=Y" +
				"&authorized_cert_yn=N" +
				"&account_hold_auth_yn=N";
		return "redirect:" + url;
	}

	@ResponseBody
	@GetMapping("/callback.html")
	public SingleResult<OauthToken> requestToken(
			@RequestParam(name = "code") String code,
			@RequestParam(name = "scope") String scope,
			@RequestParam(name = "client_info") String clientInfo,
			@RequestParam(name = "state") String state
	) {
		try {
			OauthToken oauthToken = oauthService.saveAuthorizeToken(code, scope, clientInfo, state);
			return responseService.getSingleResult(oauthToken);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(ExMessage.UNDEFINED_ERROR);
		}
	}
}

