package pl.zaprogramuj.spring.boot.webapp.excepotion.user;

public class UserExistsException extends UserException{

	private static final long serialVersionUID = 1L;
		
	public UserExistsException(String message) {
		super(message);
	}

}