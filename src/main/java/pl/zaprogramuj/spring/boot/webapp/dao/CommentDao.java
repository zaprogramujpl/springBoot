package pl.zaprogramuj.spring.boot.webapp.dao;

import pl.zaprogramuj.spring.boot.webapp.domain.post.Comment;

public interface CommentDao {

	public abstract void saveComment(Comment comment);
	public abstract Comment findById(long id);
}
