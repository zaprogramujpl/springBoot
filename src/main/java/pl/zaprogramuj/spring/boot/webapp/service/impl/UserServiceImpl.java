package pl.zaprogramuj.spring.boot.webapp.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.zaprogramuj.spring.boot.webapp.dao.UserDao;
import pl.zaprogramuj.spring.boot.webapp.domain.password.PasswordResetToken;
import pl.zaprogramuj.spring.boot.webapp.domain.user.User;
import pl.zaprogramuj.spring.boot.webapp.excepotion.user.UserExistsException;
import pl.zaprogramuj.spring.boot.webapp.excepotion.user.UserNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.service.UserService;

@Service
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public void registerUser(User newUser) throws UserExistsException {
		User user = userRepository.findByLogin(newUser.getLogin());
		
		if(user != null)
		{
			throw new UserExistsException("There is already a user with an " + newUser.getLogin() + " login");
		}
		
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		userRepository.addUser(newUser);
	}

	@Override
	public User getUserByLogin(String userLogin) throws UserNotFoundException {

		User user = userRepository.findByLogin(userLogin);

		if(user == null)
		{
			throw new UserNotFoundException(userLogin);
		}

		return user;
	}

	@Override
	public User getUserById(long id) throws UserNotFoundException {
		
		User user = userRepository.findById(id);
		
		if(user == null)
		{
			throw new UserNotFoundException(id);
		}		
		return user;
	}

	@Override
	public User getUserByEmail(String emailAddress) throws UserNotFoundException
	{	
		User user = userRepository.findByEmailAddress(emailAddress);
		
		if(user == null)
		{
			throw new UserNotFoundException(emailAddress);
		}		
		return user;
	}

	@Override
	public User getUserByName(String userName) throws UserNotFoundException {

		User user = userRepository.findByName(userName);

		if(user == null)
		{
			throw new UserNotFoundException(userName);
		}
		return user;
	}

	@Override
	public void addUewPasswordResetTokenToUser(User user)
	{	
		PasswordResetToken passwordResetToken = createNewPasswordResetToken();
		passwordResetToken.setUser(user);
		user.setPasswordResetToken(passwordResetToken);
		
		userRepository.updateUser(user);
	}
	
	@Override
	public void updateUserPassword(User updatedUser, String password) throws UserNotFoundException
	{
		User user = getUserByEmail(updatedUser.getEmailAddress());
		user.setPassword(passwordEncoder.encode(password));
	}

	@Override
	public User updateUser(String updatedUserEmail, User userInformation) throws UserNotFoundException {

		User user = userRepository.findByEmailAddress(updatedUserEmail);
		if(user == null)
		{
			throw new UserNotFoundException(updatedUserEmail);
		}
		user.setUserName(userInformation.getUserName());
		user.setEmailAddress(userInformation.getEmailAddress());
		user.setPhone(userInformation.getPhone());
		return user;
	}

	private PasswordResetToken createNewPasswordResetToken()
	{
		PasswordResetToken passwordResetToken = new PasswordResetToken();
		passwordResetToken.setToken(UUID.randomUUID().toString());
		passwordResetToken.setExpiryDate(60);
		return passwordResetToken;
	}

	@Override
	public boolean isUserWithEmaillAddress(String emailAddress) {
		return userRepository.findByEmailAddress(emailAddress) != null;
	}
}
