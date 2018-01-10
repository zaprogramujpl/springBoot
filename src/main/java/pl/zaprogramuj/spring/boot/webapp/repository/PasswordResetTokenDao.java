package pl.zaprogramuj.spring.boot.webapp.repository;

import pl.zaprogramuj.spring.boot.webapp.domain.password.PasswordResetToken;

public interface PasswordResetTokenDao {
	
	public abstract PasswordResetToken findByToken(String token);
}
