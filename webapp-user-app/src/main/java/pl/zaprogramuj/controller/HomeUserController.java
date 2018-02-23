package pl.zaprogramuj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeUserController {

	@RequestMapping
	public ModelAndView mainPage()
	{
		return new ModelAndView("page/index");
	}
}
