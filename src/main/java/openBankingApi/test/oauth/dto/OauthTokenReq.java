package openBankingApi.test.oauth.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OauthTokenReq {
	String code;
	String clientId;
	String clientSecret;
	String redirectUri;
	String grantType;
}
