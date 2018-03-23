package pl.zaprogramuj.spring.boot.webapp.component.impl;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pl.zaprogramuj.spring.boot.webapp.component.CommentComponent;
import pl.zaprogramuj.spring.boot.webapp.domain.form.post.CommentForm;
import pl.zaprogramuj.spring.boot.webapp.domain.post.Comment;

@Component
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class CommentComponentImpl implements CommentComponent {
	
	@Override
	public Comment generateCommentEntityFromCommentForm(CommentForm commentForm)
	{
		Comment comment = new Comment();
		comment.setContent(commentForm.getContent());
		return comment;
	}
}
