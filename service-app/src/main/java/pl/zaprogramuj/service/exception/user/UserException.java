package pl.zaprogramuj.service.exception.user;

import pl.zaprogramuj.service.exception.WebApplicationException;

public class UserException extends WebApplicationException{

	private static final long serialVersionUID = 1L;
	
	public UserException(String message) {
		super(message);
	}
}