package openBankingApi.test.basic.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResult {
	boolean success;

	int code;

	String message;
}
