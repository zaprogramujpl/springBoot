package pl.zaprogramuj.spring.boot.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.zaprogramuj.spring.boot.webapp.domain.form.password.PasswordForgotForm;
import pl.zaprogramuj.spring.boot.webapp.domain.mail.Mail;
import pl.zaprogramuj.spring.boot.webapp.domain.mail.MailTemplateType;
import pl.zaprogramuj.spring.boot.webapp.domain.user.User;
import pl.zaprogramuj.spring.boot.webapp.excepotion.user.UserNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.util.SystemViewsName;

@Controller
@RequestMapping(value = "/forgot-password")
public class PasswordForgotController extends AbstractController
{
	private static final Logger LOGGER = LoggerFactory.getLogger(PasswordForgotController.class);
	
	@InitBinder("forgotPasswordForm")
	private void initBinding(WebDataBinder binder)
	{
		binder.setValidator(getForgotPasswordFormValidator());
	}
	
	@ModelAttribute("forgotPasswordForm")
	public PasswordForgotForm passwordForgotForm()
	{
		return new PasswordForgotForm();
	}
	
	@RequestMapping
	public ModelAndView forgotPasswordView()
	{
		return new ModelAndView(SystemViewsName.PASSWORD_FORGOT);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView forgotPasswordViewPost(@ModelAttribute("forgotPasswordForm") @Validated PasswordForgotForm form, BindingResult bindingResult)
	{
		ModelAndView modelAndView = new ModelAndView(SystemViewsName.PASSWORD_FORGOT);
		
		User user = tryGetUserByEmailAddress(form.getEmailAddress());
		
		if(bindingResult.hasErrors())
		{
			return modelAndView;
		}
		
		if(user == null)
		{
			bindingResult.rejectValue("emailAddress", "forgotPasswordFormValidator.userNotExist");
			return modelAndView;
		}
		getUserService().addUewPasswordResetTokenToUser(user);
		
		Mail mail = new Mail();
		mail.setTo(user.getEmailAddress());
		mail.setTemplateType(MailTemplateType.FORGOT_PASSWORD);
		mail.setSubject("Test");
		//getEmailService().sendEmail(mail);
		
		modelAndView.addObject("successSendingEmail", true);
		return modelAndView;
	}
		
	private User tryGetUserByEmailAddress(String emailAddress)
	{
		User user = null;	
		try
		{
			user = getUserService().getUserByEmail(emailAddress);
		} catch (UserNotFoundException e)
		{
			LOGGER.error(e.getMessage());
		}		
		return user;
	}
}
