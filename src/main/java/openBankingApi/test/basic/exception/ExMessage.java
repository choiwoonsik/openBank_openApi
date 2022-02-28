package openBankingApi.test.basic.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExMessage {

	UNDEFINED_ERROR("미정의 에러");

	private String message;
}
