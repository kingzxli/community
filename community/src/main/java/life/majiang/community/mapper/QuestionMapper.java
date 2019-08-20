package life.majiang.community.mapper;

import java.util.List;

import life.majiang.community.model.Question;

public interface QuestionMapper {

	void insert(Question question);
	Question findById(Integer id);
	List<Question> list();
	

}
