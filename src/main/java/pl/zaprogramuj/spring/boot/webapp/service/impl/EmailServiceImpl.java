package pl.zaprogramuj.spring.boot.webapp.service.impl;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import pl.zaprogramuj.spring.boot.webapp.domain.mail.Mail;
import pl.zaprogramuj.spring.boot.webapp.domain.mail.MailTemplateType;
import pl.zaprogramuj.spring.boot.webapp.service.EmailService;

@Service
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
public class EmailServiceImpl implements EmailService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	@Override
	public void sendEmail(Mail mail) {
		try {
			String html = processEmailTemplate(mail);
			MimeMessage message = generateMessageToSend(mail, html);

			sendEmail(message);
		} catch (MessagingException e) {
			LOGGER.error(e.getMessage());
		}
	}

	private MimeMessage generateMessageToSend(Mail mail, String html) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = createHelper(message);

		helper.setTo(mail.getTo());
		helper.setSubject(mail.getSubject());
		helper.setText(html, true);

		return message;
	}

	private MimeMessageHelper createHelper(MimeMessage message) throws MessagingException {
		return new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
	}

	private String processEmailTemplate(Mail mail) {
		if (mail.getTemplateType() == MailTemplateType.NONE)
			return mail.getBody() != null ? mail.getBody() : "";

		Context context = new Context();
		context.setVariables(mail.getModel());

		return templateEngine.process(mail.getTemplateType().getPath(), context);
	}

	private void sendEmail(MimeMessage message) {
		emailSender.send(message);
	}
}
