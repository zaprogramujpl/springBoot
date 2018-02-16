package pl.zaprogramuj.spring.boot.webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import pl.zaprogramuj.spring.boot.webapp.dao.UserRoleDao;
import pl.zaprogramuj.spring.boot.webapp.domain.user.UserRole;
import pl.zaprogramuj.spring.boot.webapp.service.UserRoleService;

@Service(value = "userRoleService")
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
public class UserRoleServiceImpl implements UserRoleService
{
	@Autowired
	private UserRoleDao userRoleDao;	
	
	@Override
	public UserRole findByName(String roleName)
	{
		return userRoleDao.findByName(roleName);
	}

}
