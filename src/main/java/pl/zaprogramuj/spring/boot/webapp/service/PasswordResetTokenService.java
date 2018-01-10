package pl.zaprogramuj.spring.boot.webapp.service;

import pl.zaprogramuj.spring.boot.webapp.domain.password.PasswordResetToken;

public interface PasswordResetTokenService
{
	public abstract PasswordResetToken findByToken(String token);
	public abstract void deleteToken(PasswordResetToken token);
}
