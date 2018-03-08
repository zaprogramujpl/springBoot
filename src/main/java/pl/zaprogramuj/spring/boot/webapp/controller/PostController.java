package pl.zaprogramuj.spring.boot.webapp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.zaprogramuj.spring.boot.webapp.domain.post.Post;
import pl.zaprogramuj.spring.boot.webapp.domain.user.UserRoleEnum;
import pl.zaprogramuj.spring.boot.webapp.excepotion.post.PostNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.util.SystemViewsName;

@Controller
@RequestMapping(value = PostController.MAIN_MAPPING)
public class PostController extends AbstractController
{

	public static final String MAIN_MAPPING = "/post";
	public static final String ADD_POST_MAPPING = "/add";
	public static final String PUBLISHED_POST = "/published";
	public static final String BASIC_POST_URL = MAIN_MAPPING
			+ PUBLISHED_POST;

	@ModelAttribute("basicPostUrl")
	public String basicPostUrl()
	{
		return BASIC_POST_URL;
	}

	@GetMapping
	public ModelAndView postsPage()
	{
		ModelAndView mnv = new ModelAndView(SystemViewsName.POST_LIST_PAGE);

		mnv.addObject("posts", getPostService().getAllPosts());
		mnv.addObject("postsPage", true);
		return mnv;
	}

	@GetMapping(value = ADD_POST_MAPPING)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView addPostGET()
	{
		return new ModelAndView(SystemViewsName.INDEX);
	}
	
	@GetMapping(value = PUBLISHED_POST + "/{postUrlAddress}")
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
