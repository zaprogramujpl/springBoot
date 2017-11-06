package pl.zaprogramuj.spring.boot.webapp.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping("/")
	public ModelAndView indexView()
	{
		ModelAndView mnv = new ModelAndView("index");
		mnv.addObject("imageOneText", "Text image one.");
		mnv.addObject("imageTwoText", "Text image two.");
		mnv.addObject("imageThreeText", "Text image three.");
		return mnv;
	}
}
