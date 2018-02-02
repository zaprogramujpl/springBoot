package pl.zaprogramuj.spring.boot.webapp.repository.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import pl.zaprogramuj.spring.boot.webapp.domain.user.User;
import pl.zaprogramuj.spring.boot.webapp.repository.AbstractDao;
import pl.zaprogramuj.spring.boot.webapp.repository.UserDao;

@Repository
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao
{

	@Override
	public User findByLogin(String login) 
	{
		Session session = getEntityMenager().unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();	
		
		CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);	
		Root<User> rootUser = criteriaQuery.from(User.class);
		criteriaQuery.select(rootUser).where(builder.equal(rootUser.get("login"), login));		
		Query<User> query = session.createQuery(criteriaQuery);
		
		return query.getResultList().isEmpty() ? null : query.getSingleResult();
	}

	@Override
	public void addUser(User user) 
	{
		persist(user);		
	}

	@Override
	public User findById(long id) 
	{
		return getByPrimaryKey(id);
	}

	@Override
	public User findByName(String username) {
		Session session = getEntityMenager().unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
		Root<User> rootUser = criteriaQuery.from(User.class);
		criteriaQuery.select(rootUser).where(builder.equal(rootUser.get("username"), username));
		Query<User> query = session.createQuery(criteriaQuery);

		return query.getResultList().isEmpty() ? null : query.getSingleResult();
	}

	@Override
	public User findByEmailAddress(String emailAddress)
	{
		Session session = getEntityMenager().unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();	
		
		CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);	
		Root<User> rootUser = criteriaQuery.from(User.class);
		criteriaQuery.select(rootUser).where(builder.equal(rootUser.get("emailAddress"), emailAddress));		
		Query<User> query = session.createQuery(criteriaQuery);
		
		return query.getResultList().isEmpty() ? null : query.getSingleResult();
	}
	
	@Override
	public User updateUser(User user)
	{
		return merge(user);
	}
}
