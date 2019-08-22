package life.majiang.community.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;

@Controller
public class IndexController {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private QuestionService questionService;

	@GetMapping("/")
	public String index(HttpServletRequest request,Model model,Integer page,Integer size) {
		size=5;
		if(page<1||page==null) {
			page=1;
		}

		Cookie[] cookies=request.getCookies();
		if(cookies!=null&&cookies.length!=0) {
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
		
		PaginationDTO pagination=questionService.list(page,size);
		model.addAttribute("pagination", pagination);
		
		return "index";
		
	}
}
