package pl.zaprogramuj.spring.boot.webapp.webapp.excepotion;
public class WebApplicationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public WebApplicationException(String message)
	{
		super();
		this.message = message;
	}
}