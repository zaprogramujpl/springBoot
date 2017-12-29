package pl.zaprogramuj.spring.boot.webapp.webapp.service;

import pl.zaprogramuj.spring.boot.webapp.webapp.domain.user.UserProfile;
import pl.zaprogramuj.spring.boot.webapp.webapp.excepotion.user.UserExistsException;
import pl.zaprogramuj.spring.boot.webapp.webapp.excepotion.user.UserNotFoundException;

public interface UserService {

	public abstract void registerUser(UserProfile user) throws UserExistsException;
	public abstract UserProfile getUserByLogin(String userLogin);
	public abstract UserProfile getUserById(long id) throws UserNotFoundException;
}
