package pl.zaprogramuj.service.exception.user;
public class UserExistsException extends UserException{

	private static final long serialVersionUID = 1L;
		
	public UserExistsException(String message) {
		super(message);
	}

}