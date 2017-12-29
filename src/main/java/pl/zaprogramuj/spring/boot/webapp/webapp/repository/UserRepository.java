package pl.zaprogramuj.spring.boot.webapp.webapp.repository;

import pl.zaprogramuj.spring.boot.webapp.webapp.domain.user.UserProfile;

public interface UserRepository {

	public abstract UserProfile findByLogin(String userLogin);
	public abstract UserProfile findById(long id);
	public abstract void addUser(UserProfile user);
}
