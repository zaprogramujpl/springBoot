package pl.zaprogramuj.spring.boot.webapp.component;

import pl.zaprogramuj.spring.boot.webapp.domain.form.post.CommentForm;
import pl.zaprogramuj.spring.boot.webapp.domain.post.Comment;

public interface CommentComponent {
	public abstract Comment generateCommentEntityFromCommentForm(CommentForm commentForm);
}
