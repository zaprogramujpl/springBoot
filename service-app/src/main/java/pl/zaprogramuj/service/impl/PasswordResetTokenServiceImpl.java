package pl.zaprogramuj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import pl.zaprogramuj.dao.PasswordResetTokenDao;
import pl.zaprogramuj.domain.password.PasswordResetToken;
import pl.zaprogramuj.service.PasswordResetTokenService;

@Service
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService
{
	@Autowired
	private PasswordResetTokenDao passwordResetTokenDao;

	@Override
	public PasswordResetToken findByToken(String token)
	{
		return passwordResetTokenDao.findByToken(token);
	}

	@Override
	public void deleteToken(PasswordResetToken token)
	{
		passwordResetTokenDao.deleteToken(token);
	}
}
