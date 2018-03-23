package pl.zaprogramuj.spring.boot.webapp.service;

import pl.zaprogramuj.spring.boot.webapp.domain.post.Comment;
import pl.zaprogramuj.spring.boot.webapp.excepotion.comment.CommentNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.excepotion.post.PostNotFoundException;

public interface CommentService {
	public abstract void addCommentToPost(Comment comment, long postId) throws PostNotFoundException;
	public abstract void addCommentToComment(Comment newComment, long parentId);
	public abstract Comment getCommentById(long id) throws CommentNotFoundException;
}
