package pl.zaprogramuj.spring.boot.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.zaprogramuj.spring.boot.webapp.domain.form.post.CommentForm;
import pl.zaprogramuj.spring.boot.webapp.domain.post.Comment;
import pl.zaprogramuj.spring.boot.webapp.domain.post.Post;
import pl.zaprogramuj.spring.boot.webapp.domain.user.UserRoleEnum;
import pl.zaprogramuj.spring.boot.webapp.excepotion.post.PostNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.util.SystemViewsName;

@Controller
@RequestMapping(value = PostController.MAIN_MAPPING)
public class PostController extends AbstractController {

	public static final String MAIN_MAPPING = "/post";
	public static final String PUBLISHED_POST_URL = "/published";
	public static final String BASIC_POST_URL = MAIN_MAPPING + PUBLISHED_POST_URL;

	public static final String ADD_COMMENT_MAPPING = "/addCommentToPost";
	public static final String ADD_COMMENT_URL = MAIN_MAPPING + ADD_COMMENT_MAPPING;

	@ModelAttribute("basicPostUrl")
	public String basicPostUrl() {
		return BASIC_POST_URL;
	}

	@ModelAttribute("addCommentUrl")
	public String addCommentUrl() {
		return ADD_COMMENT_URL;
	}

	@GetMapping
	public ModelAndView postsPage(@RequestParam(value = "edit", required = false) String postEditView) {
		ModelAndView mnv = null;
		mnv = getLoggedUserInformationComponent().userHasRole("ROLE_" + UserRoleEnum.ADMIN.toString())
				&& Boolean.TRUE.toString().equals(postEditView) ? new ModelAndView(SystemViewsName.EDIT_POST_LIST_PAGE)
						: new ModelAndView(SystemViewsName.POST_LIST_PAGE);

		mnv.addObject("posts", getPostService().getAllPosts());
		mnv.addObject("postsPage", true);
		return mnv;
	}

	@GetMapping(value = PUBLISHED_POST_URL + "/{postUrlAddress}")
	public ModelAndView publishedPost(@PathVariable(value = "postUrlAddress") String postUrlAddress,
			@RequestParam(value = "edit", required = false) String postEditView) throws PostNotFoundException {

		ModelAndView mnv = null;

		mnv = getLoggedUserInformationComponent().userHasRole("ROLE_" + UserRoleEnum.ADMIN.toString())
				&& Boolean.TRUE.toString().equals(postEditView) ? new ModelAndView(SystemViewsName.EDIT_POST_PAGE)
						: new ModelAndView(SystemViewsName.POST_PAGE);

		Post post = getPostService().getPostByUrlAddress(postUrlAddress);
		mnv.addObject("post", post);
		mnv.addObject("commentForm", new CommentForm());
		return mnv;
	}

	@PostMapping(value = ADD_COMMENT_MAPPING)
	public ModelAndView addComentToPost(@ModelAttribute("postCommentForm") @Validated CommentForm commentForm,
			BindingResult bindingResult, RedirectAttributes ra) throws PostNotFoundException {
		ModelAndView mnv = new ModelAndView(SystemViewsName.REDIRECT_TO_MAIN_PAGE);
		saveCommentPost(commentForm);
		return mnv;
	}

	private void saveCommentPost(CommentForm commentForm) throws PostNotFoundException {
		Comment comment = getCommentComponent().generateCommentEntityFromCommentForm(commentForm);
		comment.setAuthor(getLoggedUserInformationComponent().tryGetLoggedUser());

		if (commentForm.getParentId() == 0) {
			getCommentService().addCommentToPost(comment, commentForm.getPostId());
		} else {
			getCommentService().addCommentToComment(comment, commentForm.getParentId());
		}
	}
}
