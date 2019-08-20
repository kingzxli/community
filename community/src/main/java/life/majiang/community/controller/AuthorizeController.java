package life.majiang.community.controller;

import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.provider.GithubProvider;

@Controller
public class AuthorizeController {

	@Autowired
	private GithubProvider githubProvider;
	
	@Autowired
	private UserMapper userMapper;
	
	@Value("${github.client.id}")
	private String clientId;
	
	@Value("${github.client.secret}")
	private String clientSecret;
	
	@Value("${github.redirect.uri}")
	private String redirectUri;
	
	
 
	@GetMapping("/callback")
	public  String callback(String code,String state,Model model,HttpServletResponse response) {
		AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
		accessTokenDTO.setClientId(clientId);
		accessTokenDTO.setClientSecret(clientSecret);
		accessTokenDTO.setCode(code);
		accessTokenDTO.setRedirectUri(redirectUri);
		accessTokenDTO.setState(state);
		
		String accessToken=githubProvider.getAccessToken(accessTokenDTO);
		GithubUser githubUser=githubProvider.getUser(accessToken);
		if(githubUser!=null&&githubUser.getId()!=null) {
			User user=new User();
			String token=UUID.randomUUID().toString();
			user.setToken(token);
			user.setName(githubUser.getName());
			user.setAccountId(githubUser.getId());
			user.setGmtCreate(System.currentTimeMillis());
			user.setGmtModified(user.getGmtCreate());
			user.setAvatarUrl(githubUser.getAvatarUrl());
			
			userMapper.insert(user);
			//1.用户信息写入session
			//request.getSession().setAttribute("user", githubUser); 
			
			//2.用户信息写入cookie
			response.addCookie(new Cookie("token", token));

			return "redirect:/";
		}else {
			return "redirect:/";
		}
		
	}
}
