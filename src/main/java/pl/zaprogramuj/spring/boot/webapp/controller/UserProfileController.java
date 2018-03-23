package pl.zaprogramuj.spring.boot.webapp.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.zaprogramuj.spring.boot.webapp.domain.user.User;
import pl.zaprogramuj.spring.boot.webapp.excepotion.user.UserNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.util.SystemViewsName;

@Controller
@RequestMapping(value = UserProfileController.BASE_MAPPING)
public class UserProfileController extends AbstractController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileController.class);

	public static final String BASE_MAPPING = "/profile";

	@GetMapping
	public ModelAndView userProfilePage() {
		ModelAndView mnv = new ModelAndView();
		User loggedUserProfile = null;
		try {
			loggedUserProfile = getUserService()
					.getUserByLogin(getLoggedUserInformationComponent().tryGedLoggedUserName());
		} catch (UserNotFoundException | NullPointerException e) {
			LOGGER.error("Not Found Logged User in DB");
			mnv.setViewName(SystemViewsName.REDIRECT_TO_MAIN_PAGE);
			return mnv;
		}
		// TODO: Think about whether to leave it like this. Should the user's password
		// be displayed?
		loggedUserProfile.setPassword("");

		mnv.addObject("userProfile", loggedUserProfile);
		mnv.setViewName(SystemViewsName.USER_PROFILE);
		return mnv;
	}

	@PostMapping
	public ModelAndView updateUserProfile(@ModelAttribute("userProfile") User modelAttributeUser, RedirectAttributes ra,
			BindingResult bindingResult, Locale locale) {
		ModelAndView mnv = new ModelAndView(SystemViewsName.REDIRECT_TO_USER_PROFILE_BASE_MAPPING);
		User loggedUser = null;

		try {
			loggedUser = getUserService().getUserByLogin(getLoggedUserInformationComponent().tryGedLoggedUserName());
		} catch (UserNotFoundException | NullPointerException e) {
			// TODO: MKT - A mechanism should be created to handle such an exception.
			LOGGER.error("Not Found Logged User in DB - Critical Error");
			return mnv;
		}

		if (!loggedUser.getEmailAddress().equals(modelAttributeUser.getEmailAddress()) && getUserService().isUserWithEmaillAddress(modelAttributeUser.getEmailAddress())) {
			LOGGER.info("This Email Address is assigned to another systems user.");
			ra.addFlashAttribute("error",
					getMessageSource().getMessage("userProfileUpdate.error.emailAddressAssignedToOtherUser", null, locale));
			return mnv;
		}

		try {
			getUserService().updateUser(loggedUser.getEmailAddress(), modelAttributeUser);
		} catch (UserNotFoundException e) {
			LOGGER.error("Not Found Logged User in DB");
			ra.addFlashAttribute("error",
					getMessageSource().getMessage("userProfileUpdate.error.updatError", null, locale));
			return mnv;
		}
		return mnv;
	}
}
