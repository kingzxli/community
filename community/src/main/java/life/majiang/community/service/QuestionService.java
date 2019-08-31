package life.majiang.community.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.exception.CustomizeException;
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
		Integer totalPage;
		Integer totalCount=questionMapper.count();

		if(totalCount%size==0) {
			totalPage=totalCount/size;
		}else {
			totalPage=totalCount/size+1;
		}
		
		if(page<1) {
			page=1;
		}
		
		if(page>totalPage) {
			page=totalPage;
		}
		paginationDTO.setPagination(totalPage, page);
		
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

	public PaginationDTO listByUserId(Long userId, Integer page, Integer size) {
		PaginationDTO paginationDTO=new PaginationDTO();
		Integer totalPage;
		Integer totalCount=questionMapper.countByUserId(userId);
		if(totalCount%size==0) {
			totalPage=totalCount/size;
		}else {
			totalPage=totalCount/size+1;
		}
		
		if(page<1) {
			page=1;
		}
		
		if(page>totalPage) {
			page=totalPage;
		}
		paginationDTO.setPagination(totalPage, page);
		
		
		Integer offset=size*(page-1);
		List<Question> questionList=questionMapper.listByUserId(userId,offset,size);
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

	public QuestionDTO getById(Long id) {
		Question question=questionMapper.findById(id);
		if(question==null) {
			throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
		}
		QuestionDTO questionDTO=new QuestionDTO();
		BeanUtils.copyProperties(question, questionDTO);
		User user=userMapper.findById(question.getCreator());
		questionDTO.setUser(user);
		return questionDTO;
	}

	public void createOrUpdate(Question question) {
		if(question.getId()==null) {
			question.setGmtCreate(System.currentTimeMillis());
			question.setGmtModified(question.getGmtCreate());
			questionMapper.insert(question);
		}else {
			Question updateQuestion=new Question();
			updateQuestion.setId(question.getId());
			updateQuestion.setGmtModified(System.currentTimeMillis());
			updateQuestion.setTitle(question.getTitle());
			updateQuestion.setDescription(question.getDescription());
			updateQuestion.setTag(question.getTag());
			
			int update=questionMapper.update(updateQuestion);
			if(update!=1) {
				throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
			}
		}
		
	}

	public void incView(Long id) {
		Question question=questionMapper.findById(id);
		Integer viewCount=question.getViewCount();
		if(viewCount==null) {
			viewCount=0;
		}
		question.setViewCount(viewCount+1);
		questionMapper.update(question);
		
	}

}