package pl.zaprogramuj.spring.boot.webapp.webapp.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import pl.zaprogramuj.spring.boot.webapp.dao.UserDao;
import pl.zaprogramuj.spring.boot.webapp.domain.user.User;
import pl.zaprogramuj.spring.boot.webapp.excepotion.user.UserExistsException;
import pl.zaprogramuj.spring.boot.webapp.excepotion.user.UserNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.service.UserService;
import pl.zaprogramuj.spring.boot.webapp.webapp.configuration.ApplicationContextConfigurationServiceTest;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ApplicationContextConfigurationServiceTest.class })
public class UserServiceImplTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserDao mockUserRepository;

	// test for method : findByLogin [BEGIN]
	@Test
	public void shouldFindUserByLogin() throws UserNotFoundException {
		User user = new User();
		user.setLogin("test");

		when(mockUserRepository.findByLogin(user.getLogin())).thenReturn(user);

		assertEquals(user, userService.getUserByLogin(user.getLogin()));
	}

	@Test(expected = UserNotFoundException.class)
	public void shouldReturnNullWhenThereIsNoUserWithGivenLogin() throws UserNotFoundException {
		when(mockUserRepository.findByLogin("test")).thenReturn(null);

		userService.getUserByLogin("test");
	}
	// test for method : findByLogin [END]

	// Tests for method : registerUser [BEGIN]
	@Test(expected = UserExistsException.class)
	public void shouldThrowExceptionWhenTriedAddExistingUser() throws UserExistsException {
		User user = new User();
		user.setLogin("test");

		when(mockUserRepository.findByLogin(user.getLogin())).thenReturn(user);

		userService.registerUser(user);
	}
	// Tests for method : registerUser [END]

	// Tests for method : getUserById [BEGIN]

	@Test
	public void shouldFindUserWithGivenId() throws UserNotFoundException {
		User user = new User();
		user.setId(1);
		
		when(mockUserRepository.findById(user.getId())).thenReturn(user);
		
		assertEquals(user, userService.getUserById(user.getId()));
	}

	@Test(expected = UserNotFoundException.class)
	public void shouldThrowExceptionWhenThereIsNotUserWithGivenId() throws UserNotFoundException {
		when(mockUserRepository.findById(1)).thenReturn(null);
		userService.getUserById(1);
	}
	// Tests for method : getUserById [END]

	// Test for method : updateUser [BEGIN]
	@Test
	public void shouldReturnNotNullObject() throws UserNotFoundException {
		User updatedUser = Mockito.mock(User.class);

		when(updatedUser.getPassword()).thenReturn("password");
		when(updatedUser.getEmailAddress()).thenReturn("emailAddress");
		when(mockUserRepository.findByEmailAddress("emailAddress")).thenReturn(Mockito.mock(User.class));

		assertNotNull(userService.updateUser("emailAddress", updatedUser));
	}

	@Test(expected = UserNotFoundException.class)
	public void shouldThrowExceptionIfUserWitlEmailIsNotExist() throws UserNotFoundException {
		User updatedUser = Mockito.mock(User.class);

		when(updatedUser.getEmailAddress()).thenReturn("emailAddress");
		when(mockUserRepository.findByEmailAddress("emailAddress")).thenReturn(null);

		userService.updateUser("emailAddress", updatedUser);
	}

	@Test
	public void shouldReturnUserWithChangedDate() throws UserNotFoundException {
		//given
		User userInformation = new User();
		userInformation.setEmailAddress("emailAddress");
		userInformation.setFirstName("firstName");
		userInformation.setLastName("lastName");
		userInformation.setPhone("phonenumber");
		userInformation.setPassword("password");
		User repositoryUser = new User();
		repositoryUser.setEmailAddress("emailAddress");

		//when
		when(mockUserRepository.findByEmailAddress("emailAddress")).thenReturn(repositoryUser);

		//then
		User updatedUser = userService.updateUser("emailAddress", userInformation);
		assertEquals(userInformation.getFirstName(), updatedUser.getFirstName());
		assertEquals(userInformation.getLastName(), updatedUser.getLastName());
		assertEquals(userInformation.getPhone(), updatedUser.getPhone());
	}
	// Test for method : updateUser [END]

	// Test for method : isUserWithEmailAddress [BEGIN]
	@Test
	public void shouldReturnTrueIfExistUserWithEmaill()
	{
		when(mockUserRepository.findByEmailAddress("email")).thenReturn(Mockito.mock(User.class));

		assertTrue(userService.isUserWithEmaillAddress("email"));
	}
	// Test for method : isUserWithEmailAddress [END]
}
