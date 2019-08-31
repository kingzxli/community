package life.majiang.community.dto;

import life.majiang.community.exception.CustomizeErrorCode;
import lombok.Data;

@Data
public class ResultDTO {
	private Integer code;
	private String message;
	
	public static ResultDTO errorof(Integer code,String message) {
		ResultDTO resultDTO=new ResultDTO();
		resultDTO.setCode(code);
		resultDTO.setMessage(message);
		return resultDTO;
	}
	
	public static ResultDTO errorof(CustomizeErrorCode errorCode) {
		return errorof(errorCode.getCode(),errorCode.getMessage());
	}
	
	public static ResultDTO okOf() {
		ResultDTO resultDTO=new ResultDTO();
		resultDTO.setCode(200);
		resultDTO.setMessage("请求成功");
		return resultDTO;
	}

}
