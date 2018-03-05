package pl.zaprogramuj.spring.boot.webapp.excepotion.post;

import pl.zaprogramuj.spring.boot.webapp.excepotion.WebApplicationException;

public class PostException extends WebApplicationException
{
	public static final String EMPTY_OR_NULL_URL_ADDRESS = "Post's url cannot by empty or null";

	private static final long serialVersionUID = 5704438078321435981L;

	public PostException(String message)
	{
		super(message);
	}
}
