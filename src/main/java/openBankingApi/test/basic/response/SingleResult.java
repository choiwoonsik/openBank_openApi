package openBankingApi.test.basic.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SingleResult<T> extends CommonResult {
	private T data;
}
