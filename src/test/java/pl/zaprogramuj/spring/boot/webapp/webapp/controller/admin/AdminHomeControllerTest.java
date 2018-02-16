package pl.zaprogramuj.spring.boot.webapp.webapp.controller.admin;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import pl.zaprogramuj.spring.boot.webapp.configuration.WebSecurityConfig;
import pl.zaprogramuj.spring.boot.webapp.controller.admin.AdminHomeController;
import pl.zaprogramuj.spring.boot.webapp.controller.admin.AdminPostController;
import pl.zaprogramuj.spring.boot.webapp.util.SystemViewsName;
import pl.zaprogramuj.spring.boot.webapp.webapp.configuration.ApplicationContextConfigurationControllerTest;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
@Import(value = { WebSecurityConfig.class, ApplicationContextConfigurationControllerTest.class })
public class AdminHomeControllerTest
{
	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(roles = "NOT_ADMIN")
	public void shouldReturnForibiddenStatusIfUserIsNotAdmin() throws Exception
	{
		mockMvc.perform(get(AdminHomeController.BASIC_ADMIN_MAPPING)).andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(roles = "ADMIN")
	public void shouldReturnAdminBasicPageView() throws Exception
	{
		mockMvc.perform(get(AdminHomeController.BASIC_ADMIN_MAPPING)).andExpect(status().isOk())
				.andExpect(model().attribute("adminPostsUrl", AdminPostController.BASIC_ADMIN_POST_MAPPING))
				.andExpect(model().attribute("adminAddNewPost",
								  AdminPostController.BASIC_ADMIN_POST_MAPPING
								+ AdminPostController.ADMIN_MENU_POST_MANAGEMENT_ADD_POST))
				.andExpect(view().name(SystemViewsName.ADMIN_INDEX_PAGE));
	}
}
