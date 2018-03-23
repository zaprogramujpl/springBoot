package pl.zaprogramuj.spring.boot.webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.zaprogramuj.spring.boot.webapp.dao.CommentDao;
import pl.zaprogramuj.spring.boot.webapp.domain.post.Comment;
import pl.zaprogramuj.spring.boot.webapp.domain.post.Post;
import pl.zaprogramuj.spring.boot.webapp.excepotion.comment.CommentNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.excepotion.post.PostNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.service.CommentService;
import pl.zaprogramuj.spring.boot.webapp.service.PostService;

@Service
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentDao commentDao;
	
	@Override
	public void addCommentToPost(Comment comment, long postId) throws PostNotFoundException {
		Post post = postService.getPostById(postId);
		comment.setPost(post);
		commentDao.saveComment(comment);
	}

	@Override
	public void addCommentToComment(Comment newComment, long oldCommentId) {
		Comment oldComment = commentDao.findById(oldCommentId);
		if(oldComment != null) {
			newComment.setParent(oldComment);
			newComment.setPost(oldComment.getPost());
		}		
		commentDao.saveComment(newComment);
	}

	@Override
	public Comment getCommentById(long id) throws CommentNotFoundException {
		Comment commentById = commentDao.findById(id);
		if(commentById == null)
			throw new CommentNotFoundException(id);
		return commentById;
	}
}
