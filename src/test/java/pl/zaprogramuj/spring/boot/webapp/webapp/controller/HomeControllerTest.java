package pl.zaprogramuj.spring.boot.webapp.webapp.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import pl.zaprogramuj.spring.boot.webapp.component.LoggedUserInformationComponent;
import pl.zaprogramuj.spring.boot.webapp.configuration.WebSecurityConfig;
import pl.zaprogramuj.spring.boot.webapp.controller.HomeController;
import pl.zaprogramuj.spring.boot.webapp.domain.user.UserRoleEnum;
import pl.zaprogramuj.spring.boot.webapp.util.SystemViewsName;
import pl.zaprogramuj.spring.boot.webapp.webapp.configuration.ApplicationContextConfigurationControllerTest;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
@Import(value = { WebSecurityConfig.class, ApplicationContextConfigurationControllerTest.class })
public class HomeControllerTest
{

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private LoggedUserInformationComponent mockLoggedUserinformationComponent;
	
	@Test
	public void shouldReturnIndexPage() throws Exception
	{
		mockMvc.perform(get(HomeController.BASIC_MAPPING))
			.andExpect(status().isOk())
			.andExpect(view().name(SystemViewsName.INDEX));
	}
	
	@Test
	@WithMockUser(roles = "ADMIN")
	public void shouldReturnEditIndexPage() throws Exception
	{	
		when(mockLoggedUserinformationComponent.userHasRole("ROLE_" + UserRoleEnum.ADMIN.toString())).thenReturn(true);
		
		mockMvc.perform(get(HomeController.BASIC_MAPPING).param("edit", "true"))
		.andExpect(view().name(SystemViewsName.EDIT_INDEX));
	}
}
