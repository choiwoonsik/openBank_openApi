package openBankingApi.test.basic.exception;

public class BusinessException extends RuntimeException {
	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(ExMessage message) {
		super(message.getMessage());
	}
}
