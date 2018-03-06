package pl.zaprogramuj.spring.boot.webapp.excepotion.pagecharacteristics;

public class PageCharacteristicsNotExistsException extends PageCharacteristicsException
{
	private static final long serialVersionUID = -8517851780468006348L;
	
	public PageCharacteristicsNotExistsException(String message)
	{
		super(message);
	}

}
