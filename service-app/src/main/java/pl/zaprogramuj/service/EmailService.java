package pl.zaprogramuj.service;

import pl.zaprogramuj.domain.mail.Mail;

public interface EmailService
{
	public abstract void sendEmail(Mail mail);
}