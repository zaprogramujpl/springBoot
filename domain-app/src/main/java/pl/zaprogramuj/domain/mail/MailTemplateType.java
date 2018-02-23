package pl.zaprogramuj.domain.mail;
public enum MailTemplateType
{
	FORGOT_PASSWORD("email/email-forgot-password-template"),
	NONE("");
	
	private String path;
	
	private MailTemplateType(String path)
	{
		this.path = path;
	}

	public String getPath()
	{
		return path;
	}
}