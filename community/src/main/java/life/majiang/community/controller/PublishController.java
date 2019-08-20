package life.majiang.community.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;

@Controller
public class PublishController {
	
	@Autowired
	private QuestionMapper questionMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("/publish")   //get请求渲染页面
	public String publish() {
		return "publish";
	}
	
	@PostMapping("/publish")   //post请求数据
	public String doPublish(String title,String description,String tag,HttpServletRequest request,Model model) {

		model.addAttribute("title", title);
		model.addAttribute("description", description);
		model.addAttribute("tag", tag);	
		
		if(title==null||title=="") {
			model.addAttribute("error", "标题不能为空");
			return "publish";
		}
		
		if(description==null||description=="") {
			model.addAttribute("error", "问题补充不能为空");
			return "publish";
		}
		
		if(tag==null||tag=="") {
			model.addAttribute("error", "标签不能为空");
			return "publish";
		}
		
		Cookie[] cookies=request.getCookies();
		User user=null;
		if(cookies!=null&&cookies.length!=0) {
		for(Cookie cookie:request.getCookies()) {
			if(cookie.getName().equals("token")) {
				user=userMapper.findUserByToken(cookie.getValue());
				if(user!=null) {
					request.getSession().setAttribute("user", user);
				}
				break;
			}
		}
		}
		if(user==null) {
			model.addAttribute("error","用户未登录");
			return "publish";
		}
		
		Question question=new Question();
		question.setTitle(title);
		question.setDescription(description);
		question.setTag(tag);
		question.setCreator(user.getId());
		question.setGmtCreate(System.currentTimeMillis());
		question.setGmtModified(question.getGmtCreate());
		
		questionMapper.insert(question);
		return "redirect:/";
		
	}

}
