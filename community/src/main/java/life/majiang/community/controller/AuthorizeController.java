package life.majiang.community.controller;

import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.model.User;
import life.majiang.community.provider.GithubProvider;
import life.majiang.community.service.UserService;

@Controller
public class AuthorizeController {

	@Autowired
	private GithubProvider githubProvider;
	
	@Autowired
	private UserService userService;
	
	@Value("${github.client.id}")
	private String clientId;
	
	@Value("${github.client.secret}")
	private String clientSecret;
	
	@Value("${github.redirect.uri}")
	private String redirectUri;
	
	
	
 
	@GetMapping("/callback")
	public  String callback(String code,String state,Model model,HttpServletResponse response) {
		AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
		accessTokenDTO.setClient_id(clientId);
		accessTokenDTO.setClient_secret(clientSecret);
		accessTokenDTO.setCode(code);
		accessTokenDTO.setRedirect_uri(redirectUri);
		accessTokenDTO.setState(state);
		
		String accessToken=githubProvider.getAccessToken(accessTokenDTO);
		GithubUser githubUser=githubProvider.getUser(accessToken);
		if(githubUser!=null&&githubUser.getId()!=null) {
			User user=new User();
			String token=UUID.randomUUID().toString();
			user.setToken(token);
			user.setName(githubUser.getName());
			user.setAccountId(githubUser.getId());
			user.setAvatarUrl(githubUser.getAvatarUrl());
			
			userService.createOrUpdate(user);
			//1.用户信息写入session
			//request.getSession().setAttribute("user", githubUser); 
			
			//2.用户信息写入cookie
			response.addCookie(new Cookie("token", token));

			return "redirect:/";
		}else {
			return "redirect:/";
		}
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		request.getSession().removeAttribute("user");
		Cookie cookie=new Cookie("token",null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		return "redirect:/";
		
	}
}
