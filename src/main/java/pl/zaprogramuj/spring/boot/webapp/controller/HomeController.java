package pl.zaprogramuj.spring.boot.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.zaprogramuj.spring.boot.webapp.domain.form.user.UserProfileForm;
import pl.zaprogramuj.spring.boot.webapp.domain.user.User;
import pl.zaprogramuj.spring.boot.webapp.domain.user.UserRole;
import pl.zaprogramuj.spring.boot.webapp.domain.user.UserRoleEnum;
import pl.zaprogramuj.spring.boot.webapp.excepotion.user.UserExistsException;
import pl.zaprogramuj.spring.boot.webapp.util.SystemViewsName;

@Controller
public class HomeController extends AbstractController
{
	@InitBinder
	private void initBinding(WebDataBinder binder)
	{
		binder.setValidator(getUserFormValidator());
	}

	@RequestMapping("/")
	public ModelAndView indexView()
	{
		return new ModelAndView(SystemViewsName.INDEX);
	}

	@RequestMapping("/login")
	public ModelAndView loginForm()
	{
		return new ModelAndView(SystemViewsName.LOGIN_PAGE);
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registerUserGET()
	{
		ModelAndView mnv = new ModelAndView(SystemViewsName.REGISTER_USER);
		mnv.addObject("userProfileForm", new UserProfileForm());
		return mnv;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registerUserPOST(@ModelAttribute("userProfileForm") @Validated UserProfileForm userProfileForm, BindingResult bindingResult)
	{
		ModelAndView mnv = new ModelAndView();

		if (bindingResult.hasErrors())
		{
			mnv.setViewName(SystemViewsName.REGISTER_USER);
			return mnv;
		}

		try
		{
			registerUser(userProfileForm);
			mnv.setViewName(SystemViewsName.INDEX);
		} catch (UserExistsException e)
		{
			mnv.addObject("userExistError", "userExistError");
			mnv.setViewName(SystemViewsName.REGISTER_USER);
		}

		return mnv;
	}

	private void registerUser(UserProfileForm userProfileForm) throws UserExistsException
	{
		User user = userProfileForm.getUser();
		UserRole userRole = getUserRoleService().findByName(UserRoleEnum.USER.getUserRole());
		user.getRoles().add(userRole);

		getUserService().registerUser(user);
	}
}
