package pl.zaprogramuj.dao;

import pl.zaprogramuj.domain.user.UserRole;

public interface UserRoleDao
{
	public abstract void initUserProfileRoles();
	public abstract UserRole findByName(String roleName);
}