package life.majiang.community.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionMapper questionMapper;
	
	@Autowired
	private UserMapper userMapper;

	public PaginationDTO list(Integer page,Integer size) {
		PaginationDTO paginationDTO=new PaginationDTO();
		Integer totalCount=questionMapper.count();
		paginationDTO.setPagination(totalCount, page, size);

		
		if(page>paginationDTO.getTotalPage()) {
			page=paginationDTO.getTotalPage();
		}
		
		
		
		Integer offset=size*(page-1);
		List<Question> questionList=questionMapper.list(offset,size);
		List<QuestionDTO> questionDTOList=new ArrayList<QuestionDTO>();
		
		for(Question question:questionList) {
			QuestionDTO questionDTO=new QuestionDTO();
			BeanUtils.copyProperties(question, questionDTO);
			User user=userMapper.findById(question.getCreator());
			questionDTO.setUser(user);
			questionDTOList.add(questionDTO);
		}
		paginationDTO.setQuestion(questionDTOList);

		return paginationDTO;
	}

}