package pl.zaprogramuj.spring.boot.webapp.dao.impl;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import pl.zaprogramuj.spring.boot.webapp.dao.AbstractDao;
import pl.zaprogramuj.spring.boot.webapp.dao.CommentDao;
import pl.zaprogramuj.spring.boot.webapp.domain.post.Comment;

@Repository
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
public class CommentDaoImpl extends AbstractDao<Long, Comment> implements CommentDao {
	
	@Override
	public void saveComment(Comment comment) {
		persist(comment);
	}

	@Override
	public Comment findById(long id) {
		return getByPrimaryKey(id);
	}
}
