package openBankingApi.test.basic.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ListResult<T> extends CommonResult {
	private List<T> data;
}
