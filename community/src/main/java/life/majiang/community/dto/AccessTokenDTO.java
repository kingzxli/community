package life.majiang.community.dto;

import lombok.Data;

//dto 数据传输对象 
@Data
public class AccessTokenDTO {
	private String clientId;
	private String clientSecret;
	private String code;
	private String redirectUri;
	private String state;
	
}
