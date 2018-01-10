package pl.zaprogramuj.spring.boot.webapp.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.zaprogramuj.spring.boot.webapp.domain.form.user.UserProfileForm;
import pl.zaprogramuj.spring.boot.webapp.util.Constants;

@Component(value = "userFormValidator")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class UserFormValidator implements Validator{
	
	private Pattern pattern;
	
	public UserFormValidator() {
		pattern = Pattern.compile(Constants.EMAIL_PATTERN);
	}
	
	@Override
	public boolean supports(Class<?> paramClass) {
		return UserProfileForm.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "userFormValidator.login.null");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "userFormValidator.login.null");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "userFormValidator.login.null");
				
		UserProfileForm userProfileForm = (UserProfileForm) target;		
		if(!userProfileForm.getPassword().equals(userProfileForm.getPasswordConfirm()))
		{
			errors.rejectValue("password", "userFormValidator.notmatch.password");
			errors.rejectValue("passwordConfirm", "userFormValidator.notmatch.password");
		}
		
		Matcher matcher = pattern.matcher(userProfileForm.getEmailAddress());
		
		if(!matcher.matches())
		{
			errors.rejectValue("emailAddress", "userFormValidator.incorrect");
		}
		
	}
}