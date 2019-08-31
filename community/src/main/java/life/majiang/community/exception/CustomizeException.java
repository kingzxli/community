package life.majiang.community.exception;

public class CustomizeException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private Integer code;
	private String message;
	
	
	public CustomizeException(ICustomizeErrorCode errorCode) {
		this.code=errorCode.getCode();
		this.message=errorCode.getMessage();
	}
	
	public String getMessage() {
		return message;
	}
	
	public Integer getCode() {
		return code;
	}
}
