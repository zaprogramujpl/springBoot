package pl.zaprogramuj.spring.boot.webapp.webapp.configuration;

import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;

import pl.zaprogramuj.spring.boot.webapp.service.EmailService;
import pl.zaprogramuj.spring.boot.webapp.service.PasswordResetTokenService;
import pl.zaprogramuj.spring.boot.webapp.service.SystemPropertiesService;
import pl.zaprogramuj.spring.boot.webapp.service.UserRoleService;
import pl.zaprogramuj.spring.boot.webapp.service.UserService;
import pl.zaprogramuj.spring.boot.webapp.validation.ForgotPasswordFormValidator;
import pl.zaprogramuj.spring.boot.webapp.validation.UserFormValidator;

@Configuration
public class ApplicationContextConfigurationControllerTest {
	@Bean
	public UserService mockUserService() {
		return Mockito.mock(UserService.class);
	}

	@Bean
	public SystemPropertiesService mockSystemPropertiesService() {
		return Mockito.mock(SystemPropertiesService.class);
	}

	@Bean
	public UserRoleService mockUserRoleService() {
		return Mockito.mock(UserRoleService.class);
	}

	@Bean
	public PasswordResetTokenService mockPasswordResetTokenService() {
		return Mockito.mock(PasswordResetTokenService.class);
	}

	@Bean
	public EmailService mockEmailService() {
		return Mockito.mock(EmailService.class);
	}

	@Bean(name = "userFormValidator")
	public Validator mockUserFormValidator() {
		return Mockito.mock(UserFormValidator.class);
	}

	@Bean(name = "forgotPasswordFormValidator")
	public Validator mockForgotPasswordFormValidator() {
		return Mockito.mock(ForgotPasswordFormValidator.class);
	}
}
