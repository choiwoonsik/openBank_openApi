package openBankingApi.test.oauth.api;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import openBankingApi.test.oauth.dto.AuthorizeReqDto;
import openBankingApi.test.oauth.dto.OauthTokenReq;
import openBankingApi.test.oauth.dto.OauthTokenRes;
import openBankingApi.test.oauth.service.OauthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.print.attribute.standard.Media;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OauthApi {

	private final OauthService oauthService;

	@GetMapping("/oauth/authorizeCode")
	public String requestAuthorizeCode() {
		String url = "https://testapi.openbanking.or.kr/oauth/2.0/authorize?response_type=code&client_id=901765e4-1f64-448b-9961-ca7e2c1bb9b3&redirect_uri=http://localhost:9090/oauth/callback.html&scope=login inquiry transfer&client_info=woonsik&state=12345678901234567890123456789012&auth_type=0&lang=kor&cellphone_cert_yn=Y&authorized_cert_yn=N&account_hold_auth_yn=N";
		return "redirect:" + url;
	}

	@GetMapping("/oauth/callback.html")
	public String requestToken(
			Model model,
			@RequestParam(name = "code") String code,
			@RequestParam(name = "scope") String scope,
			@RequestParam(name = "client_info") String client_info,
			@RequestParam(name = "state") String state
	) {
		try {
			model = oauthService.saveAuthorizeToken(model, code, scope, client_info, state);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
		return "/oauth/signUp";
	}
}

