package openBankingApi.test.oauth.dto;

import lombok.Data;
import openBankingApi.test.oauth.entity.OauthToken;

@Data
public class OauthTokenRes {
	String token_type;
	String access_token;
	String expires_in;
	String refresh_token;
	String scope;
	Long user_seq_no;

	public OauthToken toEntity() {
		return OauthToken.builder()
				.token_type(token_type)
				.access_token(access_token)
				.expires_in(expires_in)
				.refresh_token(refresh_token)
				.scope(scope)
				.user_seq_no(user_seq_no)
				.build();
	}
}
