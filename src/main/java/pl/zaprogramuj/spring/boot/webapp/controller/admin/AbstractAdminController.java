package pl.zaprogramuj.spring.boot.webapp.controller.admin;

import org.springframework.web.bind.annotation.ModelAttribute;

import pl.zaprogramuj.spring.boot.webapp.controller.AbstractController;

public class AbstractAdminController extends AbstractController {
	
	@ModelAttribute(name = "adminPostsUrl")
	public String adminPostMapping() {
		return AdminPostController.BASIC_MAPPING;
	}

	@ModelAttribute(name = "adminAddNewPost")
	public String addPostAdminMapping() {
		return AdminPostController.BASIC_MAPPING + AdminPostController.ADMIN_MENU_POST_MANAGEMENT_ADD_POST;
	}
}
