package pl.zaprogramuj.spring.boot.webapp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.zaprogramuj.spring.boot.webapp.domain.post.Post;
import pl.zaprogramuj.spring.boot.webapp.domain.user.UserRoleEnum;
import pl.zaprogramuj.spring.boot.webapp.excepotion.post.PostNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.util.SystemViewsName;

@Controller
@RequestMapping(value = PostController.POST_CONTROLLER_MAIN_MAPPING)
public class PostController extends AbstractController
{

	public static final String POST_CONTROLLER_MAIN_MAPPING = "/post";
	public static final String POST_CONTROLLER_ADD_POST_MAPPING = "/add";
	public static final String POST_CONTROLLER_PUBLISHED_POST = "/published";
	public static final String POST_CONTROLLER_BASIC_POST_URL = POST_CONTROLLER_MAIN_MAPPING
			+ POST_CONTROLLER_PUBLISHED_POST;

	@ModelAttribute("basicPostUrl")
	public String basicPostUrl()
	{
		return POST_CONTROLLER_BASIC_POST_URL;
	}

	@GetMapping
	public ModelAndView postsPage()
	{
		ModelAndView mnv = new ModelAndView(SystemViewsName.POST_LIST_PAGE);

		mnv.addObject("posts", getPostService().getAllPosts());
		mnv.addObject("postsPage", true);
		return mnv;
	}

	@GetMapping(value = POST_CONTROLLER_ADD_POST_MAPPING)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView addPostGET()
	{
		return new ModelAndView(SystemViewsName.INDEX);
	}
	
	@GetMapping(value = POST_CONTROLLER_PUBLISHED_POST + "/{postUrlAddress}")
	public ModelAndView publishedPost(@PathVariable(value = "postUrlAddress") String postUrlAddress,
			@RequestParam(value = "edit", required = false) String postEditView) throws PostNotFoundException
	{
		ModelAndView mnv = null;

		mnv = getLoggedUserInformationComponent().userHasRole("ROLE_" + UserRoleEnum.ADMIN.toString())	&& Boolean.TRUE.toString().equals(postEditView) 
				? new ModelAndView(SystemViewsName.EDIT_POST_PAGE)
				: new ModelAndView(SystemViewsName.POST_PAGE);

		Post post = getPostService().getPostByUrlAddress(postUrlAddress);
		mnv.addObject("post", post);
		return mnv;
	}
}