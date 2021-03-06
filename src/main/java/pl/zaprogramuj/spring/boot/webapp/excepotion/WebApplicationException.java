package pl.zaprogramuj.spring.boot.webapp.excepotion;
public class WebApplicationException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private final String message;
	
	public WebApplicationException(String message)
	{
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage()
	{
		return message;
	}
}