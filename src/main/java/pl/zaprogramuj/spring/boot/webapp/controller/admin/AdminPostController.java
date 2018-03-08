package pl.zaprogramuj.spring.boot.webapp.controller.admin;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.zaprogramuj.spring.boot.webapp.domain.post.Post;
import pl.zaprogramuj.spring.boot.webapp.domain.user.User;
import pl.zaprogramuj.spring.boot.webapp.excepotion.post.PostException;
import pl.zaprogramuj.spring.boot.webapp.util.SystemViewsName;

@Controller
@RequestMapping(value = AdminPostController.BASIC_MAPPING)
public class AdminPostController extends AbstractAdminController
{
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminPostController.class);
	
	public static final String BASIC_MAPPING = AdminHomeController.BASIC_MAPPING + "/posts";
	public static final String ADMIN_MENU_POST_MANAGEMENT_ADD_POST = "/add";
	
	@GetMapping
	public ModelAndView postPageGET()
	{
		return new ModelAndView(SystemViewsName.ADMIN_POSTS_PAGE);
	}
	
	@GetMapping(value = ADMIN_MENU_POST_MANAGEMENT_ADD_POST)
	public ModelAndView addPostGET()
	{
		ModelAndView mnv = new ModelAndView(SystemViewsName.ADMIN_ADD_POST_PAGE);
		
		mnv.addObject("postObject", new Post());
		return mnv;
	}
	
	@PostMapping(value = ADMIN_MENU_POST_MANAGEMENT_ADD_POST)
	public ModelAndView addPostPOST(@ModelAttribute("postObject") Post post, RedirectAttributes ra)
	{
		ModelAndView mnv = new ModelAndView(SystemViewsName.ADMIN_ADD_POST_PAGE);
		
		try
		{
			addLoggedUserToPost(post);
			getPostService().addPost(post);
		} catch (PostException e)
		{
			LOGGER.error("Post Exception: " + e.getMessage());
			return mnv;
		}
		
		return new ModelAndView("redirect:" + BASIC_MAPPING);
	}
	
	private void addLoggedUserToPost(Post post)
	{
		User user = getLoggedUserInformationComponent().tryGetLoggedUser();
		post.setCreationDate(LocalDateTime.now());
		post.setAuthor(user);
	}
}
