package life.majiang.community.mapper;

import life.majiang.community.model.User;

public interface UserMapper {

	void insert(User user);
	User findUserByToken(String token);

}
