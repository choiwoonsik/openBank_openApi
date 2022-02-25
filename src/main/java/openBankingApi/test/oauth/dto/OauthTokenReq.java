package openBankingApi.test.oauth.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OauthTokenReq {
	String code;
	String client_id;
	String client_secret;
	String redirect_uri;
	String grant_type;
}
