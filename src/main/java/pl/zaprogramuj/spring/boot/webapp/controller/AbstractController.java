package pl.zaprogramuj.spring.boot.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;

import pl.zaprogramuj.spring.boot.webapp.service.PasswordResetTokenService;
import pl.zaprogramuj.spring.boot.webapp.service.SystemPropertiesService;
import pl.zaprogramuj.spring.boot.webapp.service.UserRoleService;
import pl.zaprogramuj.spring.boot.webapp.service.UserService;

@Controller
public abstract class AbstractController 
{	
	@Autowired
	private SystemPropertiesService systemProperties;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private PasswordResetTokenService passwordResetTokenService;
	
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
}