package life.majiang.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import life.majiang.community.dto.CommentDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.service.CommentService;
import life.majiang.community.service.QuestionService;

@Controller
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/question/{id}")
	public String question(@PathVariable(name="id")Long id,Model model) {
		QuestionDTO question=questionService.getById(id);
		List<CommentDTO> comments=commentService.getByQuestionId(id);
		
		questionService.incView(id);
		model.addAttribute("question", question);
		model.addAttribute("comments", comments);
		return "question";
		
	}

}
