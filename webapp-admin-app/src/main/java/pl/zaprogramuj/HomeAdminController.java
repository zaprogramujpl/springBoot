package pl.zaprogramuj;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

@RequestMapping("/admin")
public class HomeAdminController {

	@RequestMapping
	public ModelAndView mainAdminPage()
	{
		System.out.println("test");
		return new ModelAndView("page/index");
	}
}
