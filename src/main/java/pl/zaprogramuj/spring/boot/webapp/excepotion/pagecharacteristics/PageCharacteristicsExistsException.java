package pl.zaprogramuj.spring.boot.webapp.excepotion.pagecharacteristics;

public class PageCharacteristicsExistsException extends PageCharacteristicsException
{
	private static final long serialVersionUID = 1151971131133927084L;

	public PageCharacteristicsExistsException(String message)
	{
		super(message);
	}
}
