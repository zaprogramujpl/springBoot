package pl.zaprogramuj.spring.boot.webapp.webapp.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import pl.zaprogramuj.spring.boot.webapp.webapp.configuration.ApplicationContextConfigurationServiceTest;
import pl.zaprogramuj.spring.boot.webapp.webapp.domain.user.UserProfile;
import pl.zaprogramuj.spring.boot.webapp.webapp.excepotion.user.UserExistsException;
import pl.zaprogramuj.spring.boot.webapp.webapp.excepotion.user.UserNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.webapp.repository.UserRepository;
import pl.zaprogramuj.spring.boot.webapp.webapp.service.UserService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ApplicationContextConfigurationServiceTest.class })
public class UserServiceImplTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	// test for method : findByLogin [BEGIN]
	@Test
	public void shouldFindUserByLogin() {
		UserProfile userProfile = new UserProfile();
		userProfile.setLogin("test");

		when(userRepository.findByLogin(userProfile.getLogin())).thenReturn(userProfile);

		assertEquals(userProfile, userService.getUserByLogin(userProfile.getLogin()));
	}

	@Test
	public void shouldReturnNullWhenThereIsNoUserWithGivenLogin() {
		when(userRepository.findByLogin("test")).thenReturn(null);

		assertEquals(null, userService.getUserByLogin("test"));
	}
	// test for method : findByLogin [END]

	// Tests for method : registerUser [BEGIN]
	@Test(expected = UserExistsException.class)
	public void shouldThrowExceptionWhenTriedAddExistingUser() throws UserExistsException {
		UserProfile userProfile = new UserProfile();
		userProfile.setLogin("test");

		when(userRepository.findByLogin(userProfile.getLogin())).thenReturn(userProfile);

		userService.registerUser(userProfile);
	}
	// Tests for method : registerUser [END]

	// Tests for method : getUserById [BEGIN]

	@Test
	public void shouldFindUserWithGivenId() throws UserNotFoundException {
		UserProfile userProfile = new UserProfile();
		userProfile.setId(1);
		
		when(userRepository.findById(userProfile.getId())).thenReturn(userProfile);
		
		assertEquals(userProfile, userService.getUserById(userProfile.getId()));
	}

	@Test(expected = UserNotFoundException.class)
	public void shouldThrowExceptionWhenThereIsNotUserWithGivenId() throws UserNotFoundException {
		UserProfile userProfile = new UserProfile();
		userProfile.setId(1);
		
		when(userRepository.findById(userProfile.getId())).thenReturn(null);
		
		assertEquals(userProfile, userService.getUserById(userProfile.getId()));
	}

	// Tests for method : getUserById [END]
}
