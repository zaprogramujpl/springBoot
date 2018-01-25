package pl.zaprogramuj.spring.boot.webapp.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.zaprogramuj.spring.boot.webapp.domain.password.PasswordResetToken;
import pl.zaprogramuj.spring.boot.webapp.domain.user.User;
import pl.zaprogramuj.spring.boot.webapp.excepotion.user.UserExistsException;
import pl.zaprogramuj.spring.boot.webapp.excepotion.user.UserNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.repository.UserDao;
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
	public User getUserByLogin(String userLogin) {
		return userRepository.findByLogin(userLogin);
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
	public void addUewPasswordResetTokenToUser(User user)
	{	
		PasswordResetToken passwordResetToken = createNewPasswordResetToken();
		passwordResetToken.setUser(user);
		user.setPasswordResetToken(passwordResetToken);
		
		userRepository.updateUser(user);
	}
	
	@Override
	public void updateUserPassword(User newUser, String password) throws UserNotFoundException
	{
		User user = getUserByEmail(newUser.getEmailAddress());
		
		user.setPassword(passwordEncoder.encode(password));	
		//TODO: MKT - do przetestowania czy jest to potrzebne
		userRepository.updateUser(user);
	}	
	
	private PasswordResetToken createNewPasswordResetToken()
	{
		PasswordResetToken passwordResetToken = new PasswordResetToken();
		passwordResetToken.setToken(UUID.randomUUID().toString());
		passwordResetToken.setExpiryDate(60);
		return passwordResetToken;
	}

	@Override
	public void updateUserAfterResetPassword(User user)
	{
		// TODO Auto-generated method stub
	}
}
