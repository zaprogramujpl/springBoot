package pl.zaprogramuj.spring.boot.webapp.webapp.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import pl.zaprogramuj.spring.boot.webapp.component.CommentComponent;
import pl.zaprogramuj.spring.boot.webapp.component.LoggedUserInformationComponent;
import pl.zaprogramuj.spring.boot.webapp.configuration.WebSecurityConfig;
import pl.zaprogramuj.spring.boot.webapp.controller.PostController;
import pl.zaprogramuj.spring.boot.webapp.domain.form.post.CommentForm;
import pl.zaprogramuj.spring.boot.webapp.domain.post.Comment;
import pl.zaprogramuj.spring.boot.webapp.domain.post.Post;
import pl.zaprogramuj.spring.boot.webapp.domain.user.UserRoleEnum;
import pl.zaprogramuj.spring.boot.webapp.service.CommentService;
import pl.zaprogramuj.spring.boot.webapp.service.PostService;
import pl.zaprogramuj.spring.boot.webapp.util.SystemViewsName;
import pl.zaprogramuj.spring.boot.webapp.webapp.configuration.ApplicationContextConfigurationControllerTest;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
@Import(value = { WebSecurityConfig.class, ApplicationContextConfigurationControllerTest.class })
public class PostControllerTest
{
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private LoggedUserInformationComponent mockLoggedUserinformationComponent;
	
	@Autowired
	private CommentComponent mockCommentComponent;

	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;

	@Before
	public void setUp()
	{
		Mockito.reset(mockLoggedUserinformationComponent);
	}
	
	@Test
	public void shouldReturnPostsPage() throws Exception
	{
		mockMvc.perform(get(PostController.MAIN_MAPPING))
				.andExpect(view().name(SystemViewsName.POST_LIST_PAGE))
				.andExpect(model().attributeExists("posts", "basicPostUrl"));
	}
	
	@Test
	@WithMockUser(roles = "ADMIN")
	public void shouldReturnEditPostsPage() throws Exception
	{
		
		when(mockLoggedUserinformationComponent.userHasRole("ROLE_" + UserRoleEnum.ADMIN.toString())).thenReturn(true);

		mockMvc.perform(get(PostController.MAIN_MAPPING).param("edit", "true"))
				.andExpect(view().name(SystemViewsName.EDIT_POST_LIST_PAGE));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void shouldReturnPostPageAdminVersionIfUserHasRoleAdminAndRequestParamIsTrue() throws Exception
	{
		String publishedExamplePostUrl = PostController.MAIN_MAPPING
				+ PostController.PUBLISHED_POST_URL + "/testPost";

		when(mockLoggedUserinformationComponent.userHasRole("ROLE_" + UserRoleEnum.ADMIN.toString())).thenReturn(true);

		mockMvc.perform(get(publishedExamplePostUrl).param("edit", "true"))
				.andExpect(view().name(SystemViewsName.EDIT_POST_PAGE));
	}

	@Test
	public void shouldReturnPostPageWithPostObjectModel() throws Exception
	{
		String publishedExamplePostUrl = PostController.MAIN_MAPPING
				+ PostController.PUBLISHED_POST_URL + "/testPost";

		Post mockPost = Mockito.mock(Post.class);
		
		when(postService.getPostByUrlAddress("testPost")).thenReturn(mockPost);
		
		mockMvc.perform(get(publishedExamplePostUrl)).andExpect(status().isOk())
				.andExpect(model().attributeExists("post")).andExpect(view().name(SystemViewsName.POST_PAGE));
	}
	
	//POST MAPPING
	@Test
	public void shouldAddCommentToPost() throws Exception
	{
		String url = PostController.MAIN_MAPPING + PostController.ADD_COMMENT_MAPPING;		
		CommentForm commentForm = Mockito.mock(CommentForm.class);	
		Comment comment = Mockito.mock(Comment.class);

		when(mockCommentComponent.generateCommentEntityFromCommentForm(commentForm)).thenReturn(comment);
		
		mockMvc.perform(post(url).flashAttr("postCommentForm", commentForm));
		
		verify(commentService, times(1)).addCommentToPost(comment, commentForm.getPostId());
	}
}
