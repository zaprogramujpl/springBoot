package pl.zaprogramuj.spring.boot.webapp.dao;

import pl.zaprogramuj.spring.boot.webapp.service.UserRoleService;

public interface UserRoleDao extends UserRoleService
{
	public abstract void initUserProfileRoles();
}
