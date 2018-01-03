package pl.zaprogramuj.spring.boot.webapp.webapp.repository.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import pl.zaprogramuj.spring.boot.webapp.webapp.domain.user.UserProfile;
import pl.zaprogramuj.spring.boot.webapp.webapp.repository.AbstractDao;
import pl.zaprogramuj.spring.boot.webapp.webapp.repository.UserRepository;

@Repository
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
public class UserRepositoryImpl extends AbstractDao<Long, UserProfile> implements UserRepository{

	@Override
	public UserProfile findByLogin(String login) {
		Session session = getEntityMenager().unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<UserProfile> criteriaQuery = builder.createQuery(UserProfile.class);
		Root<UserProfile> rootUser = criteriaQuery.from(UserProfile.class);
		criteriaQuery.select(rootUser).where(builder.equal(rootUser.get("login"), login));		
		Query<UserProfile> q = session.createQuery(criteriaQuery);
		
		return q.getResultList().isEmpty() ? null : q.getSingleResult();
	}

	@Override
	public void addUser(UserProfile user) {
		persist(user);		
	}

	@Override
	public UserProfile findById(long id) {
		return getByPrimaryKey(id);
	}
}
