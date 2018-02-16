package pl.zaprogramuj.spring.boot.webapp.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import pl.zaprogramuj.spring.boot.webapp.dao.AbstractDao;
import pl.zaprogramuj.spring.boot.webapp.dao.UserRoleDao;
import pl.zaprogramuj.spring.boot.webapp.domain.user.UserRole;
import pl.zaprogramuj.spring.boot.webapp.domain.user.UserRoleEnum;

@Repository
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
public class UserRoleDaoImpl extends AbstractDao<Long, UserRole> implements UserRoleDao
{
	
	@Autowired
	@Qualifier("transactionManager")
	protected PlatformTransactionManager txManager;

	@PostConstruct
	private void init()
	{
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult()
		{
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status)
			{
				initUserProfileRoles();
			}
		});
	}

	@Override
	public UserRole findByName(String roleName)
	{
		Session session = getEntityMenager().unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();	
		
		CriteriaQuery<UserRole> criteriaQuery = builder.createQuery(UserRole.class);	
		Root<UserRole> rootUser = criteriaQuery.from(UserRole.class);
		criteriaQuery.select(rootUser).where(builder.equal(rootUser.get("role"), roleName));		
		Query<UserRole> query = session.createQuery(criteriaQuery);
		
		return query.getResultList().isEmpty() ? null : query.getSingleResult();
	}

	/*
	 * This is method to initializing default user profile roles.
	 */	
	@Transactional
	@Override
	public void initUserProfileRoles()
	{
		UserRoleEnum[] rolesTable = UserRoleEnum.class.getEnumConstants();
		for (UserRoleEnum roleName : rolesTable)
		{
			UserRole r = findByName(roleName.getUserRole());
			if (r == null)
			{
				UserRole role = new UserRole();
				role.setRole(roleName.getUserRole());
				persist(role);
			}
		}		
	}

}
