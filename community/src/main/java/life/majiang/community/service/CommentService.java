package life.majiang.community.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import life.majiang.community.dto.CommentDTO;
import life.majiang.community.enums.CommentTypeEnum;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.exception.CustomizeException;
import life.majiang.community.mapper.CommentMapper;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Comment;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;

@Service
public class CommentService {
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private QuestionMapper questionMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Transactional
	public void insert(Comment comment) {
		if(comment.getParentId()==null||comment.getParentId()==0) {
			throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
			
		}
		
		if(comment.getType()==null||!CommentTypeEnum.isExist(comment.getType())) {
			throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_wrong);
		}
		
		if(comment.getType()==CommentTypeEnum.COMMENT.getType()) {
			Comment dbComment=commentMapper.selectById(comment.getParentId());
			if(dbComment==null) {
				throw new CustomizeException(CustomizeErrorCode.COMENT_NOT_FOUND);
			}
			commentMapper.insert(comment);
		}else {
			Question question=questionMapper.findById(comment.getParentId());
			if(question==null) {
				throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
			}
			commentMapper.insert(comment);
			question.setCommentCount(question.getCommentCount()+1);
			questionMapper.update(question);

		}
	}

	public List<CommentDTO> getByQuestionId(Long id) {
		List<Comment> comments=commentMapper.selectByParentId(id);
		if(comments.size()==0) {
			return new ArrayList<>();
		}
		
		//获取去重的评论人
		Set<Long> commentators=comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
		List<Long> ids=new ArrayList<>();
		ids.addAll(commentators);
		//获取评论人并转化为Map
		List<User> users=userMapper.selectByIds(ids);
		Map<Long,User> userMap=users.stream().collect(Collectors.toMap(user ->user.getId(), user -> user));
		
		//转换comment为commentDTO
		List<CommentDTO> commentDTOS=comments.stream().map(comment ->{
			CommentDTO commentDTO=new CommentDTO();
			BeanUtils.copyProperties(comment, commentDTO);
			commentDTO.setUser(userMap.get(comment.getCommentator()));
			return commentDTO;
		}).collect(Collectors.toList());
		
		
		return commentDTOS;
	}
}
