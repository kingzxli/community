package life.majiang.community.mapper;

import java.util.List;

import life.majiang.community.model.User;

public interface UserMapper {

	void insert(User user);
	List<User> findUserByToken(String token);
	User findById(Long creator);
	User findByAccountId(String accountId);
	void update(User user);
	List<User> selectByIds(List<Long> ids);

}

