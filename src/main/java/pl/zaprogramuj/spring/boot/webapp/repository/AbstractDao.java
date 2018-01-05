package pl.zaprogramuj.spring.boot.webapp.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDao<PK extends Serializable, T> {
	
	private final Class<T> persistentClass;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public AbstractDao()
	{
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	protected EntityManager getEntityMenager()
	{
		return this.entityManager;
	}

	protected T getByPrimaryKey(PK pk)
	{
		return (T) entityManager.find(persistentClass, pk);
	}

	protected void persist(T entity)
	{
		entityManager.persist(entity);
	}
	
	protected T merge(T entity)
	{
		return entityManager.merge(entity);
	}
}
