package life.majiang.community.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;

@Controller
public class PublishController {
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/publish/{id}")   //get请求渲染页面
	public String edit(@PathVariable(name="id") Long id,Model model) {
		
		QuestionDTO question=questionService.getById(id);
		model.addAttribute("title", question.getTitle());
		model.addAttribute("description", question.getDescription());
		model.addAttribute("tag", question.getTag());	
		model.addAttribute("id", question.getId());	
		
		return "publish";
	}
	
	@GetMapping("/publish")   //get请求渲染页面
	public String publish() {
		return "publish";
	}
	
	@PostMapping("/publish")   //post请求数据
	public String doPublish(String title,String description,String tag,Long id,HttpServletRequest request,Model model) {

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
		
		User user=(User) request.getSession().getAttribute("user");
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
		question.setId(id);
		questionService.createOrUpdate(question);
		return "redirect:/";
		
	}

}
