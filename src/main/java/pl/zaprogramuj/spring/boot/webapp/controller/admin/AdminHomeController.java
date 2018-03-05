package pl.zaprogramuj.spring.boot.webapp.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.zaprogramuj.spring.boot.webapp.util.SystemViewsName;

@Controller
@RequestMapping(value = AdminHomeController.BASIC_ADMIN_MAPPING)
public class AdminHomeController extends AbstractAdminController
{
	public static final String BASIC_ADMIN_MAPPING = "/admin";
	
	@RequestMapping
	public ModelAndView mainAdminPage()
	{
		return new ModelAndView(SystemViewsName.ADMIN_INDEX_PAGE);
	}
}
