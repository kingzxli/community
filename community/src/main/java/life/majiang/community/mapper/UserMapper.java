package life.majiang.community.mapper;

import life.majiang.community.model.User;

public interface UserMapper {

	void insert(User user);
	User findUserByToken(String token);
	User findById(Integer creator);
	User findByAccountId(String accountId);
	void update(User user);

}

