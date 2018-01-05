package pl.zaprogramuj.spring.boot.webapp.service;

import pl.zaprogramuj.spring.boot.webapp.domain.user.UserRole;

public interface UserRoleService
{
	public abstract UserRole findByName(String roleName);
}
