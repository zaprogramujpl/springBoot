package pl.zaprogramuj.spring.boot.webapp.excepotion.password.token;

import pl.zaprogramuj.spring.boot.webapp.excepotion.WebApplicationException;

public class PasswordResetTokenException extends WebApplicationException
{
	private static final long serialVersionUID = 1L;
	
	public PasswordResetTokenException(String message)
	{
		super(message);
	}
}
