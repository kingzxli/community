package life.majiang.community.mapper;

import java.util.List;

import life.majiang.community.model.Question;

public interface QuestionMapper {

	void insert(Question question);
	Question findById(Integer id);
	List<Question> list(Integer offset,Integer size);
	Integer countByUserId(Integer userId);
	Integer count();
	List<Question> listByUserId(Integer userId, Integer offset, Integer size);

}
