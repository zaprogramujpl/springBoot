package pl.zaprogramuj.spring.boot.webapp.webapp.excepotion.user;
public class UserNotFoundException extends UserException{

	private static final long serialVersionUID = 1L;
	
	private static final String NOT_FOUND_USER_WITH_ID = "Not found user with id: ";
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
	public UserNotFoundException(long id_user)
	{
		this(NOT_FOUND_USER_WITH_ID + id_user);
	}
}