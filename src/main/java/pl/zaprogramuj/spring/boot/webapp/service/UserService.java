package pl.zaprogramuj.spring.boot.webapp.service;

import pl.zaprogramuj.spring.boot.webapp.domain.user.User;
import pl.zaprogramuj.spring.boot.webapp.excepotion.user.UserExistsException;
import pl.zaprogramuj.spring.boot.webapp.excepotion.user.UserNotFoundException;

public interface UserService {

	public abstract void registerUser(User user) throws UserExistsException;
	public abstract User getUserByLogin(String userLogin) throws UserNotFoundException;
	public abstract User getUserById(long id) throws UserNotFoundException;
	public abstract User getUserByEmail(String emailAddress) throws UserNotFoundException;
	public abstract User getUserByName(String userName) throws UserNotFoundException;
	public abstract void addUewPasswordResetTokenToUser(User user);
	public abstract void updateUserPassword(User user, String password) throws UserNotFoundException;
	public abstract User updateUser(String updatedUserEmail, User userInformation) throws UserNotFoundException;
	public abstract boolean isUserWithEmaillAddress(String emailAddress);
}
