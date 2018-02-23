package pl.zaprogramuj.dao;

import pl.zaprogramuj.domain.password.PasswordResetToken;

public interface PasswordResetTokenDao {	
	public abstract PasswordResetToken findByToken(String token);
	public abstract void deleteToken(PasswordResetToken token);
}