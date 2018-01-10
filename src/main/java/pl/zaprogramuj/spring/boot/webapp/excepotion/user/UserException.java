package pl.zaprogramuj.spring.boot.webapp.excepotion.user;

import pl.zaprogramuj.spring.boot.webapp.excepotion.WebApplicationException;

public class UserException extends WebApplicationException{

	private static final long serialVersionUID = 1L;
	
	public UserException(String message) {
		super(message);
	}
}