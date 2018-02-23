package pl.zaprogramuj.service;

import pl.zaprogramuj.domain.user.UserRole;

public interface UserRoleService
{
	public abstract UserRole findByName(String roleName);
}