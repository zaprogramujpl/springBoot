package pl.zaprogramuj.spring.boot.webapp.domain.mail;

import java.util.HashMap;
import java.util.Map;

public class Mail
{
	private String from;
	private String to;
	private String subject;
	private String body;
	private MailTemplateType templateType;
	private Map<String, Object> model;

	public Mail()
	{
		model = new HashMap<>();
		templateType = MailTemplateType.NONE;
	}

	public MailTemplateType getTemplateType()
	{
		return templateType;
	}

	public void setTemplateType(MailTemplateType templateType)
	{
		this.templateType = templateType;
	}

	public String getFrom()
	{
		return from;
	}

	public void setFrom(String from)
	{
		this.from = from;
	}

	public String getTo()
	{
		return to;
	}

	public void setTo(String to)
	{
		this.to = to;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public Map<String, Object> getModel()
	{
		return model;
	}

	public void setModel(Map<String, Object> model)
	{
		this.model = model;
	}

	public String getBody()
	{
		return body;
	}

	public void setBody(String body)
	{
		this.body = body;
	}
}
