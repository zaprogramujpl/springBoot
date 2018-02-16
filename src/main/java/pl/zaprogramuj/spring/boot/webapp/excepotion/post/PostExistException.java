package pl.zaprogramuj.spring.boot.webapp.excepotion.post;

public class PostExistException extends PostException
{
	private static final long serialVersionUID = -7262581427866973261L;

	public PostExistException(String message)
	{
		super(message);
	}
}
