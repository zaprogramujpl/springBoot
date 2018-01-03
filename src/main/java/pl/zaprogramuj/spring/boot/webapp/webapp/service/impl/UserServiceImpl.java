package pl.zaprogramuj.spring.boot.webapp.webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.zaprogramuj.spring.boot.webapp.webapp.domain.user.UserProfile;
import pl.zaprogramuj.spring.boot.webapp.webapp.excepotion.user.UserExistsException;
import pl.zaprogramuj.spring.boot.webapp.webapp.excepotion.user.UserNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.webapp.repository.UserRepository;
import pl.zaprogramuj.spring.boot.webapp.webapp.service.UserService;

@Service
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public void registerUser(UserProfile newUser) throws UserExistsException {
		UserProfile user = userRepository.findByLogin(newUser.getLogin());
		
		if(user != null)
		{
			throw new UserExistsException("There is already a user with an " + newUser.getLogin() + " login");
		}
		
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		userRepository.addUser(newUser);
	}

	@Override
	public UserProfile getUserByLogin(String userLogin) {
		return userRepository.findByLogin(userLogin);
	}

	@Override
	public UserProfile getUserById(long id) throws UserNotFoundException {
		
		UserProfile userProfile = userRepository.findById(id);
		
		if(userProfile == null)
		{
			throw new UserNotFoundException(id);
		}
		
		return userProfile;
	}

}
