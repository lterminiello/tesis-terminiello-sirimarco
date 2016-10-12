package server.home.model;

public class ErrorApi {

	private String codeError;
    private String message;

	public ErrorApi(String codeError, String message) {
		super();
		this.codeError = codeError;
		this.message = message;
	}

	public String getCodeError() {
		return codeError;
	}

	public void setCodeError(String codeError) {
		this.codeError = codeError;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}