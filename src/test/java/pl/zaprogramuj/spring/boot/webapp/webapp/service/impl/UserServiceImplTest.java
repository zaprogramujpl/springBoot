package pl.zaprogramuj.spring.boot.webapp.webapp.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import pl.zaprogramuj.spring.boot.webapp.domain.user.User;
import pl.zaprogramuj.spring.boot.webapp.excepotion.user.UserExistsException;
import pl.zaprogramuj.spring.boot.webapp.excepotion.user.UserNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.repository.UserDao;
import pl.zaprogramuj.spring.boot.webapp.service.UserService;
import pl.zaprogramuj.spring.boot.webapp.webapp.configuration.ApplicationContextConfigurationServiceTest;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ApplicationContextConfigurationServiceTest.class })
public class UserServiceImplTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserDao userRepository;

	// test for method : findByLogin [BEGIN]
	@Test
	public void shouldFindUserByLogin() {
		User user = new User();
		user.setLogin("test");

		when(userRepository.findByLogin(user.getLogin())).thenReturn(user);

		assertEquals(user, userService.getUserByLogin(user.getLogin()));
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
		User user = new User();
		user.setLogin("test");

		when(userRepository.findByLogin(user.getLogin())).thenReturn(user);

		userService.registerUser(user);
	}
	// Tests for method : registerUser [END]

	// Tests for method : getUserById [BEGIN]

	@Test
	public void shouldFindUserWithGivenId() throws UserNotFoundException {
		User user = new User();
		user.setId(1);
		
		when(userRepository.findById(user.getId())).thenReturn(user);
		
		assertEquals(user, userService.getUserById(user.getId()));
	}

	@Test(expected = UserNotFoundException.class)
	public void shouldThrowExceptionWhenThereIsNotUserWithGivenId() throws UserNotFoundException {
		User user = new User();;
		user.setId(1);
		
		when(userRepository.findById(user.getId())).thenReturn(null);
		
		assertEquals(user, userService.getUserById(user.getId()));
	}

	// Tests for method : getUserById [END]
}
