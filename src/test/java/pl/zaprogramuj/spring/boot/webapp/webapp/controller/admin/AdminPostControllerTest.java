package pl.zaprogramuj.spring.boot.webapp.webapp.controller.admin;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import pl.zaprogramuj.spring.boot.webapp.configuration.WebSecurityConfig;
import pl.zaprogramuj.spring.boot.webapp.controller.admin.AdminPostController;
import pl.zaprogramuj.spring.boot.webapp.domain.post.Post;
import pl.zaprogramuj.spring.boot.webapp.excepotion.post.PostExistException;
import pl.zaprogramuj.spring.boot.webapp.service.PostService;
import pl.zaprogramuj.spring.boot.webapp.util.SystemViewsName;
import pl.zaprogramuj.spring.boot.webapp.webapp.configuration.ApplicationContextConfigurationControllerTest;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
@Import(value = { WebSecurityConfig.class, ApplicationContextConfigurationControllerTest.class })
public class AdminPostControllerTest
{
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private PostService mockPostService;

	@Test
	@WithMockUser(roles = "ADMIN")
	public void shouldReturnAdminPostPageView() throws Exception
	{
		mockMvc.perform(get(AdminPostController.BASIC_MAPPING))
				.andExpect(status().isOk()).andExpect(view().name(SystemViewsName.ADMIN_POSTS_PAGE));
	}
	
	@Test
	@WithMockUser(roles = "ADMIN")
	public void shouldReturnErrorIfExistPostWithSameUrlAddress() throws Exception
	{
		String addPostPOST = AdminPostController.BASIC_MAPPING + AdminPostController.ADMIN_MENU_POST_MANAGEMENT_ADD_POST;
		Post postMock = Mockito.mock(Post.class);
		
		doThrow(new PostExistException("urlAddress")).when(mockPostService).addPost(postMock);
		
		mockMvc.perform(post(addPostPOST).flashAttr("postObject", postMock))
			.andExpect(view().name(SystemViewsName.ADMIN_ADD_POST_PAGE));
	}
	
	@Test
	@WithMockUser(roles = "ADMIN")
	public void shouldRedirectToPostsPageAfterAddPost() throws Exception
	{
		String addPostPOST = AdminPostController.BASIC_MAPPING + AdminPostController.ADMIN_MENU_POST_MANAGEMENT_ADD_POST;
		Post postMock = Mockito.mock(Post.class);
		
		mockMvc.perform(post(addPostPOST).flashAttr("postObject", postMock))
			.andExpect(view().name("redirect:" + AdminPostController.BASIC_MAPPING));
	}
}
