package pl.zaprogramuj.spring.boot.webapp.excepotion.pagecharacteristics;

import pl.zaprogramuj.spring.boot.webapp.excepotion.WebApplicationException;

public class PageCharacteristicsException extends WebApplicationException
{
	private static final long serialVersionUID = 9029298726271665405L;

	public PageCharacteristicsException(String message)
	{
		super(message);
	}
}
