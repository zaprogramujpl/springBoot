package pl.zaprogramuj.spring.boot.webapp.webapp.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import pl.zaprogramuj.spring.boot.webapp.dao.PageCharacteristicsDao;
import pl.zaprogramuj.spring.boot.webapp.domain.page.PageCharacteristics;
import pl.zaprogramuj.spring.boot.webapp.webapp.configuration.ApplicationContextConfigurationDaoTest;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(value = { ApplicationContextConfigurationDaoTest.class })
public class PageCharacteristicsDaoTest
{
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private PageCharacteristicsDao pageCharacteristicsDao;
	
	@Test
	public void shouldAddPageCharacteristicsToDB()
	{
		PageCharacteristics pageCharacteristics = new PageCharacteristics();
		pageCharacteristicsDao.addPageCharacteristics(pageCharacteristics);
		
		Query query = entityManager.getEntityManager().createQuery("SELECT p from PageCharacteristics p");
		
		assertEquals(1, query.getResultList().size());
	}
	
	@Test
	public void shouldUpdatePageCharacteristicsToDB()
	{
		PageCharacteristics pageCharacteristics1 = new PageCharacteristics();
		PageCharacteristics pageCharacteristics2 = new PageCharacteristics();
		
		entityManager.persist(pageCharacteristics1);
		
		pageCharacteristics2.setId(pageCharacteristics1.getId());		
		PageCharacteristics updatedPage = pageCharacteristicsDao.updatePageCharacteristics(pageCharacteristics2);
		
		Query query = entityManager.getEntityManager().createQuery("SELECT p from PageCharacteristics p");
	
		assertEquals(1, query.getResultList().size());
		assertEquals(pageCharacteristics2.getId(), updatedPage.getId());
	}
	
	@Test
	public void shouldReturnEntityByUrlAddres()
	{
		PageCharacteristics pageCharacteristics = new PageCharacteristics();
		pageCharacteristics.setUriAddress("urlAddress");
		
		entityManager.persist(pageCharacteristics);
		
		assertNotNull(pageCharacteristicsDao.findPageCharacteristicsByUriAddress("urlAddress"));
	}
}
