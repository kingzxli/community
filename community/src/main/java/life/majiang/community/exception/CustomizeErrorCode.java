package life.majiang.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
	QUESTION_NOT_FOUND(2001,"你找的问题不存在"),
	TARGET_PARAM_NOT_FOUND(2002,"你找的问题不存在"),
	NO_LOGIN(2003, "未登录不能评论,请先登录"),
	TYPE_PARAM_wrong(2004, "未登录不能评论,请先登录"),
	COMENT_NOT_FOUND(2005,"评论不能为空"),
	COMENT_IS_EMPTY(2006,"输入内容不能为空");
	
	public String getMessage() {
		return message;
	}
	public Integer getCode() {
		return code;
	}
	
	private Integer code;
	private String message;
	
	CustomizeErrorCode(Integer code,String message){
		this.code=code;
		this.message=message;
	}

}
