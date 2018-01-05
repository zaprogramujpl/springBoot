package pl.zaprogramuj.spring.boot.webapp.excepotion.user;

import org.omg.CORBA.UserException;

public class UserExistsException extends UserException{

	private static final long serialVersionUID = 1L;
		
	public UserExistsException(String message) {
		super(message);
	}

}