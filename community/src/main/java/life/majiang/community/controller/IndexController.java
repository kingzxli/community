package life.majiang.community.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;

@Controller
public class IndexController {
	
	@Autowired
	private UserMapper userMapper;

	@GetMapping("/")
	public String index(HttpServletRequest request) {
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
		for(Cookie cookie:request.getCookies()) {
			if(cookie.getName().equals("token")) {
				User user=userMapper.findUserByToken(cookie.getValue());
				if(user!=null) {
					request.getSession().setAttribute("user", user);
				}
				break;
			}
		}
		}
		return "index";
		
	}
}
