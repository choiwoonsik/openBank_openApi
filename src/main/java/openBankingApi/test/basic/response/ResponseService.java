package openBankingApi.test.basic.response;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

	public <T> SingleResult<T> getSingleResult(T data) {
		SingleResult<T> result = new SingleResult<>();
		result.setData(data);
		setSuccess(result);
		return result;
	}

	public <T> ListResult<T> getListResult(List<T> data) {
		ListResult<T> result = new ListResult<>();
		result.setData(data);
		setSuccess(result);
		return result;
	}

	public CommonResult getSuccessResult() {
		CommonResult result = new CommonResult();
		setSuccess(result);
		return result;
	}

	public CommonResult getFailResult() {
		CommonResult result = new CommonResult();
		result.setCode(CommonResponse.FAIL.getCode());
		result.setMessage(CommonResponse.FAIL.getMessage());
		return result;
	}

	private void setSuccess(CommonResult result) {
		result.setSuccess(true);
		result.setCode(CommonResponse.SUCCESS.getCode());
	}
}
