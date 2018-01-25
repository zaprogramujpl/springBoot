package pl.zaprogramuj.spring.boot.webapp.repository;

import pl.zaprogramuj.spring.boot.webapp.domain.user.User;

public interface UserDao {
	public abstract User findByLogin(String userLogin);
	public abstract User findById(long id);
	public abstract User findByName(String userName);
	public abstract void addUser(User user);
	public abstract User findByEmailAddress(String emailAddress);
	public abstract User updateUser(User user);
}
