package pl.zaprogramuj.spring.boot.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;

import pl.zaprogramuj.spring.boot.webapp.component.LoggedUserInformationComponent;
import pl.zaprogramuj.spring.boot.webapp.service.EmailService;
import pl.zaprogramuj.spring.boot.webapp.service.PasswordResetTokenService;
import pl.zaprogramuj.spring.boot.webapp.service.SystemPropertiesService;
import pl.zaprogramuj.spring.boot.webapp.service.UserRoleService;
import pl.zaprogramuj.spring.boot.webapp.service.UserService;

public abstract class AbstractController 
{	
    @Autowired
    private MessageSource messageSource;
	
	@Autowired
	private SystemPropertiesService systemProperties;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private PasswordResetTokenService passwordResetTokenService;
	
	@Autowired
	private EmailService emailService;

	@Autowired
	private LoggedUserInformationComponent loggedUserInformationComponent;
	
	@Autowired
	@Qualifier("userFormValidator")
	private Validator userFormValidator;
	
	@Autowired
	@Qualifier("forgotPasswordFormValidator")
	private Validator forgotPasswordFormValidator;
	
	@ModelAttribute("systemVersion")
	public String getSystemVersion()
	{
		return systemProperties.getSystemVersion();
	}

	public UserService getUserService()
	{
		return userService;
	}

	public UserRoleService getUserRoleService()
	{
		return userRoleService;
	}

	public Validator getUserFormValidator()
	{
		return userFormValidator;
	}

	public Validator getForgotPasswordFormValidator()
	{
		return forgotPasswordFormValidator;
	}

	public PasswordResetTokenService getPasswordResetTokenService()
	{
		return passwordResetTokenService;
	}

	public EmailService getEmailService()
	{
		return emailService;
	}

	public MessageSource getMessageSource()
	{
		return messageSource;
	}

	public LoggedUserInformationComponent getLoggedUserInformationComponent()
	{
		return loggedUserInformationComponent;
	}
}