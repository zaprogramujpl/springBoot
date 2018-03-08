package pl.zaprogramuj.spring.boot.webapp.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.zaprogramuj.spring.boot.webapp.domain.page.PageCharacteristics;
import pl.zaprogramuj.spring.boot.webapp.util.SystemViewsName;

@Controller
@RequestMapping(value = AdminHomeController.BASIC_MAPPING)
public class AdminHomeController extends AbstractAdminController
{
	public static final String BASIC_MAPPING = "/admin";
	public static final String UPDATE_PAGE_CHARACTERISTICS_MAPPING = "/updateCSS";
	
	@RequestMapping
	public ModelAndView mainAdminPage()
	{
		return new ModelAndView(SystemViewsName.ADMIN_INDEX_PAGE);
	}
	
	@PostMapping(value = UPDATE_PAGE_CHARACTERISTICS_MAPPING)
	public ModelAndView updatePageCss(@ModelAttribute("pageCharacteristics") PageCharacteristics pageCharacteristics, RedirectAttributes ra)
	{
		getPageCharacteristics().saveOrUpdatePageCharacteristics(pageCharacteristics);

		return new ModelAndView("redirect:" + pageCharacteristics.getUriAddress());
	}
}
