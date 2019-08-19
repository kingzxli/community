package life.majiang.community.provider;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
public class GithubProvider {
	
	public String getAccessToken(AccessTokenDTO accessTokenDTO) {
		  MediaType mediaType = MediaType.get("application/json; charset=utf-8");
		  OkHttpClient client = new OkHttpClient();

		  RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
		    Request request = new Request.Builder()
		        .url("https://github.com/login/oauth/access_token")
		        .post(body)
		        .build();
		 try (Response response = client.newCall(request).execute()) {
		    	String string=response.body().string();
		    	System.out.println("打印的返回token=="+string);
		    	String str=string.substring(string.indexOf("=")+1, string.indexOf("&"));
		      return str;
		    }catch(IOException e) {
		    	
		  }
		return null;
		}
	
	//0cd09a501a5099e07a8d47b5801d6801997d3cd0
	public GithubUser getUser(String accessToken) {
		OkHttpClient client = new OkHttpClient();
	    Request request = new Request.Builder()
			      .url("https://api.github.com/user?access_token="+accessToken)
			      .build();
			  
	    try {
	    	Response response = client.newCall(request).execute();
	    	String string=response.body().string();
	    	GithubUser githubUser=JSON.parseObject(string,GithubUser.class);
	    	return githubUser;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
		
	}
}