package pl.zaprogramuj.spring.boot.webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import pl.zaprogramuj.spring.boot.webapp.domain.user.User;
import pl.zaprogramuj.spring.boot.webapp.repository.PasswordResetTokenDao;
import pl.zaprogramuj.spring.boot.webapp.service.PasswordResetTokenService;

@Service
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService
{
	@Autowired
	private PasswordResetTokenDao passwordResetTokenDao;
}
