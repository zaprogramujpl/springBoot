package pl.zaprogramuj.spring.boot.webapp.excepotion.comment;

import pl.zaprogramuj.spring.boot.webapp.excepotion.WebApplicationException;

public class CommentException extends WebApplicationException{

	private static final long serialVersionUID = 1079904131141607293L;
	
	public CommentException(String message) {
		super(message);
	}
}
