package pl.zaprogramuj.spring.boot.webapp.webapp.controller;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import pl.zaprogramuj.spring.boot.webapp.component.LoggedUserInformationComponent;
import pl.zaprogramuj.spring.boot.webapp.configuration.WebSecurityConfig;
import pl.zaprogramuj.spring.boot.webapp.domain.user.User;
import pl.zaprogramuj.spring.boot.webapp.excepotion.user.UserNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.service.UserService;
import pl.zaprogramuj.spring.boot.webapp.util.SystemViewsName;
import pl.zaprogramuj.spring.boot.webapp.webapp.configuration.ApplicationContextConfigurationControllerTest;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
@Import(value = { WebSecurityConfig.class, ApplicationContextConfigurationControllerTest.class })
public class UserProfileControllerTest
{
	private static final String CONTROLLER_URL = "/profile";

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserService mockUserService;

	@Autowired
	private LoggedUserInformationComponent loggedUserInformationComponent;
	
	@Before
	public void setUp()
	{
		Mockito.reset(mockUserService);
	}
	
	//GET MAPPING
	@Test
	public void shouldRedirectToLoginPage() throws Exception
	{
		mockMvc.perform(get(CONTROLLER_URL))
			.andExpect(redirectedUrl(SystemViewsName.REDIRECT_AUTHENTICATION));
	}
	
	@Test
	@WithMockUser
	public void shouldReturnUserProfilePage() throws Exception {
		when(loggedUserInformationComponent.tryGedLoggedUserName()).thenReturn("Test");
		when(mockUserService.getUserByLogin("Test")).thenReturn(Mockito.mock(User.class));

		mockMvc.perform(get(CONTROLLER_URL))
				.andExpect(view().name(SystemViewsName.USER_PROFILE));
	}

	@Test
	@WithMockUser(username = "Test")
	public void shouldAddUserObjectToModelIfUserIsLogged() throws Exception
	{
		when(loggedUserInformationComponent.tryGedLoggedUserName()).thenReturn("Test");
		when(mockUserService.getUserByLogin("Test")).thenReturn(Mockito.mock(User.class));

		mockMvc.perform(get(CONTROLLER_URL))
			.andExpect(model().attributeExists("userProfile"));
	}

	@Test
	@WithMockUser(username = "Test")
	public void shouldRedirectToMainPageIfUserNotExistsInDB() throws Exception
	{
		when(loggedUserInformationComponent.tryGedLoggedUserName()).thenReturn("Test");
		doThrow(new UserNotFoundException("Test")).when(mockUserService).getUserByLogin("Test");

		mockMvc.perform(get(CONTROLLER_URL))
				.andExpect(view().name(SystemViewsName.REDIRECT_TO_MAIN_PAGE));
	}

	//POST MAPPING

	@Test
	@WithMockUser(username = "Test")
	public void shouldRedirectToProfilePageAfterUpdate() throws Exception
	{
		User loggedUser = Mockito.mock(User.class);
		User updatedUser = Mockito.mock(User.class);

		when(loggedUser.getEmailAddress()).thenReturn("emailAddress");
		when(updatedUser.getEmailAddress()).thenReturn("emailAddress");
		when(loggedUserInformationComponent.tryGedLoggedUserName()).thenReturn("loggedUserName");
		when(mockUserService.getUserByLogin("loggedUserName")).thenReturn(loggedUser);
		when(mockUserService.updateUser(loggedUser.getEmailAddress(), loggedUser)).thenReturn(updatedUser);

		mockMvc.perform(post(CONTROLLER_URL).flashAttr("userProfile", updatedUser))
				.andExpect(view().name("redirect:" + CONTROLLER_URL));

		verify(mockUserService, times(1)).updateUser(loggedUser.getEmailAddress(), updatedUser);
	}

	@Test
	@WithMockUser(username = "Test")
	public void shouldAddErrorMessageToModelIfChangedEmailAddressIsAssignedToOtherUser() throws Exception {
		User loggedUser = Mockito.mock(User.class);
		User userProfile = Mockito.mock(User.class);

		when(loggedUser.getEmailAddress()).thenReturn("loggedUserEmailAddress");
		when(userProfile.getEmailAddress()).thenReturn("modelAttributeEmailAddress");
		when(loggedUserInformationComponent.tryGedLoggedUserName()).thenReturn("Test");
		when(mockUserService.getUserByLogin("Test")).thenReturn(loggedUser);
		when(mockUserService.isUserWithEmaillAddress(userProfile.getEmailAddress())).thenReturn(true);

		mockMvc.perform(post(CONTROLLER_URL).flashAttr("userProfile", userProfile))
				.andExpect(flash().attributeExists("error"))
				.andExpect(view().name("redirect:" + CONTROLLER_URL));
	}
}
