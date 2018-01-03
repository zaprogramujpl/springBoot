package pl.zaprogramuj.spring.boot.webapp.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.zaprogramuj.spring.boot.webapp.webapp.domain.form.user.UserProfileForm;
import pl.zaprogramuj.spring.boot.webapp.webapp.domain.user.UserProfile;
import pl.zaprogramuj.spring.boot.webapp.webapp.excepotion.user.UserExistsException;
import pl.zaprogramuj.spring.boot.webapp.webapp.service.UserService;
import pl.zaprogramuj.spring.boot.webapp.webapp.util.SystemViewsName;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public ModelAndView indexView()
	{ 
		return new ModelAndView(SystemViewsName.INDEX);	
	}
	
	@RequestMapping("/myAccount")
	public ModelAndView myAccount()
	{
		return new ModelAndView("myAccount");
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
	public ModelAndView registerUserPOST(Model model, @ModelAttribute("userProfileForm") @Validated UserProfileForm userProfileForm, BindingResult bindingResult)
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
		UserProfile user = userProfileForm.getUser();
		//user.addRole(getUserRoleService().findByEnumValue(UserRoleEnum.USER));
		userService.registerUser(user);
	}
}
