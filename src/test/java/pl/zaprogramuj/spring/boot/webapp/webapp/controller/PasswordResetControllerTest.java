package pl.zaprogramuj.spring.boot.webapp.webapp.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import pl.zaprogramuj.spring.boot.webapp.configuration.WebSecurityConfig;
import pl.zaprogramuj.spring.boot.webapp.domain.form.password.PasswordResetForm;
import pl.zaprogramuj.spring.boot.webapp.domain.password.PasswordResetToken;
import pl.zaprogramuj.spring.boot.webapp.domain.user.User;
import pl.zaprogramuj.spring.boot.webapp.excepotion.user.UserNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.service.PasswordResetTokenService;
import pl.zaprogramuj.spring.boot.webapp.service.UserService;
import pl.zaprogramuj.spring.boot.webapp.util.SystemViewsName;
import pl.zaprogramuj.spring.boot.webapp.webapp.configuration.ApplicationContextConfigurationControllerTest;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
@Import(value = { WebSecurityConfig.class, ApplicationContextConfigurationControllerTest.class })
public class PasswordResetControllerTest
{
	private static final String CONTROLLER_URL = "/reset-password";
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PasswordResetTokenService mockPasswordResetTokenService;
	
	@Autowired
	private UserService userService;
	
	@Before
	public void setUp()
	{
		Mockito.reset(mockPasswordResetTokenService);
		Mockito.reset(userService);
	}
	
	//GET MAPPING
	@Test	
	public void shouldAddTokenToModelIfTokenDateIsNotExpired() throws Exception
	{       
        PasswordResetToken token = Mockito.mock(PasswordResetToken.class);
		token.setExpiryDate(1);

		String urlParamTokenValue = "tokenValue";

		when(mockPasswordResetTokenService.findByToken(urlParamTokenValue)).thenReturn(token);

		mockMvc.perform(get(CONTROLLER_URL).param("token", urlParamTokenValue))
				.andExpect(status().isOk())
				.andExpect(view().name(SystemViewsName.PASSWORD_RESET))
				.andExpect(model().attribute("token", token.getToken()));
		
		verify(mockPasswordResetTokenService, times(1)).findByToken(urlParamTokenValue);
		verifyNoMoreInteractions(mockPasswordResetTokenService);
	}
	
	@Test	
	public void shouldReturnResetPasswordPage() throws Exception
	{       
		mockMvc.perform(get(CONTROLLER_URL))
				.andExpect(status().isOk())
				.andExpect(view().name(SystemViewsName.PASSWORD_RESET));
	}

	@Test
	public void shouldAddErrorAttributeToModelBecauseTokenIsNull() throws Exception
	{
		when(mockPasswordResetTokenService.findByToken("token")).thenReturn(null);

		mockMvc.perform(get(CONTROLLER_URL))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("errorResetPasswordToken"));
	}

	@Test
	public void shouldAddErrorAttributeToModelBecauseTokenHasExpireDate() throws Exception
	{
		PasswordResetToken token = Mockito.mock(PasswordResetToken.class);

		when(token.isExpired()).thenReturn(true);

		mockMvc.perform(get(CONTROLLER_URL))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("errorResetPasswordToken"));
	}
	
	//POST MAPPING	
	@Test
	public void shouldRedirectToLoginPageIfExistTokenEntity() throws Exception
	{	
		PasswordResetToken token = Mockito.mock(PasswordResetToken.class);
		PasswordResetForm form = Mockito.mock(PasswordResetForm.class);
		User user = Mockito.mock(User.class);
				
		when(token.getToken()).thenReturn("tokenValue");
		when(token.getUser()).thenReturn(user);
		when(form.getToken()).thenReturn("tokenValue");
		when(mockPasswordResetTokenService.findByToken(form.getToken())).thenReturn(token);
		
		mockMvc.perform(post(CONTROLLER_URL).flashAttr("passwordResetForm", form))
				.andExpect(view().name("redirect:/login"));	
		
		verify(mockPasswordResetTokenService, times(1)).deleteToken(token);
	}
	
	@Test
	public void shouldRedirectToResetPassworPagedIfEntityTokenIsNull() throws Exception
	{
		PasswordResetForm form = Mockito.mock(PasswordResetForm.class);
		
		when(form.getToken()).thenReturn("tokenValue");
		when(mockPasswordResetTokenService.findByToken(form.getToken())).thenReturn(null);
		
		mockMvc.perform(post(CONTROLLER_URL).flashAttr("passwordResetForm", form))
				.andExpect(view().name("redirect:" + CONTROLLER_URL + "?token=" + form.getToken()));
	}
		
	@Test
	public void shouldRedirectToResetPasswordPageIfUserNotExist() throws Exception
	{
		PasswordResetForm form = Mockito.mock(PasswordResetForm.class);
		PasswordResetToken token = Mockito.mock(PasswordResetToken.class);
		User user = Mockito.mock(User.class);
		String formPassword = "password";
		
		when(form.getPassword()).thenReturn(formPassword);
		when(user.getEmailAddress()).thenReturn("emailAddress");
		when(token.getUser()).thenReturn(user);
		when(form.getToken()).thenReturn("tokenValue");
		when(mockPasswordResetTokenService.findByToken(form.getToken())).thenReturn(token);
		doThrow(new UserNotFoundException("userEmail")).when(userService).updateUserPassword(user, formPassword);
		
		mockMvc.perform(post(CONTROLLER_URL).flashAttr("passwordResetForm", form))
			.andExpect(redirectedUrl(CONTROLLER_URL+ "?token=" + form.getToken()));
	}
}
