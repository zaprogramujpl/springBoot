package pl.zaprogramuj.spring.boot.webapp.repository.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import pl.zaprogramuj.spring.boot.webapp.domain.password.PasswordResetToken;
import pl.zaprogramuj.spring.boot.webapp.repository.AbstractDao;
import pl.zaprogramuj.spring.boot.webapp.repository.PasswordResetTokenDao;

@Repository
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
public class PasswordResetTokenDaoImpl extends AbstractDao<Long, PasswordResetToken> implements PasswordResetTokenDao
{
	@Override
	public PasswordResetToken findByToken(String token) 
	{
		Session session = getEntityMenager().unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<PasswordResetToken> criteriaQuery = builder.createQuery(PasswordResetToken.class);
		Root<PasswordResetToken> root = criteriaQuery.from(PasswordResetToken.class);
		
		criteriaQuery.select(root).where(builder.equal(root.get("token"), token));		
		Query<PasswordResetToken> query = session.createQuery(criteriaQuery);
		
		return query.getSingleResult();
	}
}
