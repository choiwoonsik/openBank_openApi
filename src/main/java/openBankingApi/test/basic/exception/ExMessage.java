package openBankingApi.test.basic.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExMessage {

	UNDEFINED_ERROR("미정의 에러")
	, NOT_FOUND_ERROR("해당 요청 객체 없음")
	, DUPLICATE_ERROR("객체 중복");

	private String message;
}
