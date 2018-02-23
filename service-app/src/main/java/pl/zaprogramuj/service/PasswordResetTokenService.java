package pl.zaprogramuj.service;

import pl.zaprogramuj.domain.password.PasswordResetToken;

public interface PasswordResetTokenService
{
	public abstract PasswordResetToken findByToken(String token);
	public abstract void deleteToken(PasswordResetToken token);
}