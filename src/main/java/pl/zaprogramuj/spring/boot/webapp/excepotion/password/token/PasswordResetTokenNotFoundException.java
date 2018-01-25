package pl.zaprogramuj.spring.boot.webapp.excepotion.password.token;

public class PasswordResetTokenNotFoundException extends PasswordResetTokenException
{
	private static final long serialVersionUID = 1L;

	public PasswordResetTokenNotFoundException(String message)
	{
		super(message);
	}
}
