package pl.zaprogramuj.spring.boot.webapp.dao;

import pl.zaprogramuj.spring.boot.webapp.domain.password.PasswordResetToken;

public interface PasswordResetTokenDao {	
	public abstract PasswordResetToken findByToken(String token);
	public abstract void deleteToken(PasswordResetToken token);
}
