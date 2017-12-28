package pl.zaprogramuj.spring.boot.webapp.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping("/")
	public ModelAndView indexView()
	{ 
		return new ModelAndView("index");	
	}
	
	@RequestMapping("/myAccount")
	public ModelAndView myAccount()
	{
		return new ModelAndView("myAccount");
	}
	
	@RequestMapping("/login")
	public ModelAndView loginForm()
	{
		return new ModelAndView("loginPage/loginPage");
	}
}
