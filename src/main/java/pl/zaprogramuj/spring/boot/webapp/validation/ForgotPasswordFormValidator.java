package pl.zaprogramuj.spring.boot.webapp.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import pl.zaprogramuj.spring.boot.webapp.domain.form.password.PasswordForgotForm;
import pl.zaprogramuj.spring.boot.webapp.util.Constants;

@Component(value = "forgotPasswordFormValidator")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ForgotPasswordFormValidator implements Validator
{
	private Pattern pattern;
	
	public ForgotPasswordFormValidator()
	{
		pattern = Pattern.compile(Constants.EMAIL_PATTERN);
	}
	
	@Override
	public boolean supports(Class<?> paramClass)
	{
		return PasswordForgotForm.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors)
	{
		PasswordForgotForm userProfileForm = (PasswordForgotForm) target;	

		Matcher matcher = pattern.matcher(userProfileForm.getEmailAddress());
		
		if(!matcher.matches())
		{
			errors.rejectValue("emailAddress", "forgotPasswordFormValidator.incorrect");
		}
	}
}
