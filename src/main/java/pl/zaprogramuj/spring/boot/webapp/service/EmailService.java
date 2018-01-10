package pl.zaprogramuj.spring.boot.webapp.service;

import pl.zaprogramuj.spring.boot.webapp.domain.mail.Mail;

public interface EmailService
{
	public abstract void sendEmail(Mail mail);
}
