package openBankingApi.test.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizeReqDto {
	String code;
	String scope;
	String client_info;
	String state;
}
