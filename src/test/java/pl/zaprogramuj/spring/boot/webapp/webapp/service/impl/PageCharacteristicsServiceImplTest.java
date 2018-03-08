package pl.zaprogramuj.spring.boot.webapp.webapp.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import pl.zaprogramuj.spring.boot.webapp.dao.PageCharacteristicsDao;
import pl.zaprogramuj.spring.boot.webapp.domain.page.PageCharacteristics;
import pl.zaprogramuj.spring.boot.webapp.excepotion.pagecharacteristics.PageCharacteristicsExistsException;
import pl.zaprogramuj.spring.boot.webapp.excepotion.pagecharacteristics.PageCharacteristicsNotExistsException;
import pl.zaprogramuj.spring.boot.webapp.service.PageCharacteristicsService;
import pl.zaprogramuj.spring.boot.webapp.webapp.configuration.ApplicationContextConfigurationServiceTest;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ApplicationContextConfigurationServiceTest.class })
public class PageCharacteristicsServiceImplTest
{
	@Autowired
	private PageCharacteristicsService pageCharacteristicsService;
	
	@Autowired
	private PageCharacteristicsDao mockDao;
	
	@Test(expected = PageCharacteristicsExistsException.class)
	public void shouldThrowExceptionIfExistsPageCharacteristicsWithSameUrlAddress() throws PageCharacteristicsExistsException
	{
		PageCharacteristics pageCharacteristics = Mockito.mock(PageCharacteristics.class);
		
		when(pageCharacteristics.getUriAddress()).thenReturn("uriAddress");
		when(mockDao.findPageCharacteristicsByUriAddress("uriAddress")).thenReturn(Mockito.mock(PageCharacteristics.class));
		
		pageCharacteristicsService.addPageCharacteristics(pageCharacteristics);
	}
	
	@Test(expected = PageCharacteristicsNotExistsException.class)
	public void shouldThrowExceptionIfExistsPageCharacteristicsWithSameUrlAddressWhenWeTryUpdate() throws PageCharacteristicsNotExistsException
	{
		PageCharacteristics pageCharacteristics = Mockito.mock(PageCharacteristics.class);
		
		when(pageCharacteristics.getUriAddress()).thenReturn("uriAddress");
		when(mockDao.findPageCharacteristicsByUriAddress("uriAddress")).thenReturn(null);
		
		pageCharacteristicsService.updatePageCharacteristics(pageCharacteristics);
	}
	
	@Test
	public void shouldReturnPageCharacteristicsByUrlAddress() throws PageCharacteristicsNotExistsException
	{
		when(mockDao.findPageCharacteristicsByUriAddress("uriAddress")).thenReturn(Mockito.mock(PageCharacteristics.class));
		
		assertNotNull(pageCharacteristicsService.getPageCharacteristicsByUriAddress("uriAddress"));
	}
	
	@Test(expected = PageCharacteristicsNotExistsException.class)
	public void shouldThrowExceptionIfPageCharacteristicsNotExists() throws PageCharacteristicsNotExistsException
	{
		when(mockDao.findPageCharacteristicsByUriAddress("uriAddress")).thenReturn(null);
		
		pageCharacteristicsService.getPageCharacteristicsByUriAddress("uriAddress");
	}
	
	@Test
	public void shouldReturnTrueIfPageCharacteristicsExistInDB()
	{
		when(mockDao.findPageCharacteristicsByUriAddress("uriAddress")).thenReturn(Mockito.mock(PageCharacteristics.class));
		
		assertTrue(pageCharacteristicsService.isPageCharacteristicsWithUriAddress("uriAddress"));
	}
}
