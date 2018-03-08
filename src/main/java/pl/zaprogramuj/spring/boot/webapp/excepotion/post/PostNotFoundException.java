package pl.zaprogramuj.spring.boot.webapp.excepotion.post;

public class PostNotFoundException extends PostException
{
	private static final long serialVersionUID = -6921798892239850637L;
	
	private static final String NOT_FOUND_POST_WITH_ID = "Not found Post with id: ";
	
	public static final String NOT_FOUND_POST_WITH_URL_ADDRESS = "Not found Post with urlAddress: ";
	
	public PostNotFoundException(String urlAddress)
	{
		super(NOT_FOUND_POST_WITH_URL_ADDRESS + urlAddress);
	}
	
	public PostNotFoundException(long id)
	{
		this(NOT_FOUND_POST_WITH_ID + id);
	}
}
