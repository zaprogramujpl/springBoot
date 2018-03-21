package pl.zaprogramuj.spring.boot.webapp.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.zaprogramuj.spring.boot.webapp.domain.form.password.PasswordResetForm;
import pl.zaprogramuj.spring.boot.webapp.domain.password.PasswordResetToken;
import pl.zaprogramuj.spring.boot.webapp.domain.user.User;
import pl.zaprogramuj.spring.boot.webapp.excepotion.user.UserNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.util.SystemViewsName;

@Controller
@RequestMapping(value = PasswordResetController.BASE_MAPPING)
public class PasswordResetController extends AbstractController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PasswordResetController.class);

	public static final String BASE_MAPPING = "/reset-password";

	@ModelAttribute("passwordResetForm")
	public PasswordResetForm passwordResetForm() {
		return new PasswordResetForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getResetPasswordPage(@RequestParam(required = false, name = "token") String token,
			Locale locale) {
		ModelAndView modelAndView = new ModelAndView(SystemViewsName.PASSWORD_RESET);

		PasswordResetToken resetToken = getPasswordResetTokenService().findByToken(token);

		if (resetToken == null) {
			modelAndView.addObject("errorResetPasswordToken",
					getMessageSource().getMessage("resetPasswordFormValidator.error.token.null", null, locale));
		} else if (resetToken.isExpired()) {
			modelAndView.addObject("errorResetPasswordToken",
					getMessageSource().getMessage("resetPasswordFormValidator.error.token.expired", null, locale));
		} else {
			modelAndView.addObject("token", resetToken.getToken());
		}
		return modelAndView;
	}

	@PostMapping
	@Transactional
	public ModelAndView handelPasswordReset(@ModelAttribute("passwordResetForm") @Validated PasswordResetForm form,
			RedirectAttributes ra, Locale locale) {
		ModelAndView modelAndView = new ModelAndView();
		PasswordResetToken token = getPasswordResetTokenService().findByToken(form.getToken());

		if (token == null) {
			LOGGER.error("INCORRECT TOKEN");
			modelAndView.setViewName("redirect:/reset-password?token=" + form.getToken());
			return modelAndView;
		}
		User user = token.getUser();
		try {
			getUserService().updateUserPassword(user, form.getPassword());
		} catch (UserNotFoundException e) {
			LOGGER.error("Error class: " + e.getClass() + ", error message: " + e.getMessage());
			modelAndView.setViewName("redirect:/reset-password?token=" + form.getToken());
			return modelAndView;
		}

		user.setPasswordResetToken(null);
		getPasswordResetTokenService().deleteToken(token);

		modelAndView.setViewName("redirect:/login");
		return modelAndView;
	}
}
