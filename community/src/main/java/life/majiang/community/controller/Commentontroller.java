package life.majiang.community.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import life.majiang.community.dto.CommentCreateDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.model.Comment;
import life.majiang.community.model.User;
import life.majiang.community.service.CommentService;

@Controller
public class Commentontroller {
	
	@Autowired
	private CommentService commentService;
	
	@ResponseBody
	@RequestMapping(value="/comment",method=RequestMethod.POST)
	public Object post(@RequestBody CommentCreateDTO commentDTO,HttpServletRequest request) {
		User user=(User)request.getSession().getAttribute("user");
		if(user==null) {
			return ResultDTO.errorof(CustomizeErrorCode.NO_LOGIN);
		}
		
		if(commentDTO ==null || commentDTO.getContent()==null||commentDTO.getContent()=="") {
			return ResultDTO.errorof(CustomizeErrorCode.COMENT_IS_EMPTY);
		}
		
		Comment comment=new Comment();
		comment.setParentId(commentDTO.getParentId());
		comment.setContent(commentDTO.getContent());
		comment.setType(commentDTO.getType());
		comment.setGmtCreate(System.currentTimeMillis());
		comment.setGmtModified(System.currentTimeMillis());
		comment.setCommentator(36L);
		comment.setLikeCount(0);
		commentService.insert(comment);
		return ResultDTO.okOf();
		
	}

}
