package openBankingApi.test.basic.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import openBankingApi.test.basic.response.CommonResult;
import openBankingApi.test.basic.response.ResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

	private final ResponseService responseService;

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public CommonResult exceptionHandler(HttpServletRequest request, Exception e) {
		log.error("ERROR START======================================================================");
		log.error("오류 발생 URI >>> " + request.getMethod() + " " + request.getRequestURI() + " <<<");
		log.error(e.getMessage());
		e.printStackTrace();
		log.error("ERROR END======================================================================");
		return responseService.getFailResult();
	}
}
