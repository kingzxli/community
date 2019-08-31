package life.majiang.community.mapper;

import java.util.List;

import life.majiang.community.model.Comment;

public interface CommentMapper {

	void insert(Comment comment);

	Comment selectById(Long id);

	List<Comment> selectByParentId(Long parentId);

	
}
