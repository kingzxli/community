package life.majiang.community.mapper;

import java.util.List;
import life.majiang.community.model.Question;

public interface QuestionMapper {

	void insert(Question question);
	Question findById(Long id);
	List<Question> list(Integer offset,Integer size);
	Integer countByUserId(Long userId);
	Integer count();
	List<Question> listByUserId(Long userId, Integer offset, Integer size);
	Integer update(Question question);

}
