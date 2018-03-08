package pl.zaprogramuj.spring.boot.webapp.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import pl.zaprogramuj.spring.boot.webapp.dao.AbstractDao;
import pl.zaprogramuj.spring.boot.webapp.dao.PageCharacteristicsDao;
import pl.zaprogramuj.spring.boot.webapp.domain.page.PageCharacteristics;

@Repository
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
public class PageCharacteristicsDaoImpl extends AbstractDao<Long, PageCharacteristics> implements PageCharacteristicsDao
{

	@Override
	public void addPageCharacteristics(PageCharacteristics page)
	{
		persist(page);		
	}

	@Override
	public PageCharacteristics updatePageCharacteristics(PageCharacteristics page)
	{
		return merge(page);
	}

	@Override
	public PageCharacteristics findPageCharacteristicsByUriAddress(String urlAddress)
	{
		Session session = getEntityMenager().unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<PageCharacteristics> criteriaQuery = builder.createQuery(PageCharacteristics.class);
		Root<PageCharacteristics> root = criteriaQuery.from(PageCharacteristics.class);
		
		criteriaQuery.select(root).where(builder.equal(root.get("uriAddress"), urlAddress));		
		Query<PageCharacteristics> query = session.createQuery(criteriaQuery);
		
		return query.getResultList().isEmpty() ? null : query.getSingleResult();
	}
}
