package pl.zaprogramuj.spring.boot.webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.zaprogramuj.spring.boot.webapp.dao.PageCharacteristicsDao;
import pl.zaprogramuj.spring.boot.webapp.domain.page.PageCharacteristics;
import pl.zaprogramuj.spring.boot.webapp.excepotion.pagecharacteristics.PageCharacteristicsExistsException;
import pl.zaprogramuj.spring.boot.webapp.excepotion.pagecharacteristics.PageCharacteristicsNotExistsException;
import pl.zaprogramuj.spring.boot.webapp.service.PageCharacteristicsService;

@Service
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
@Transactional
public class PageCharacteristicsServiceImpl implements PageCharacteristicsService
{
	@Autowired
	private PageCharacteristicsDao pageCharacteristicsDao;

	@Override
	public void addPageCharacteristics(PageCharacteristics page) throws PageCharacteristicsExistsException
	{
		PageCharacteristics pageCharacteristics = pageCharacteristicsDao.findPageCharacteristicsByUriAddress(page.getUriAddress());
		
		if(pageCharacteristics != null)
			throw new PageCharacteristicsExistsException(page.getUriAddress());
		
		pageCharacteristicsDao.addPageCharacteristics(page);
	}

	@Override
	public PageCharacteristics updatePageCharacteristics(PageCharacteristics page) throws PageCharacteristicsNotExistsException
	{
		PageCharacteristics pageCharacteristics = pageCharacteristicsDao.findPageCharacteristicsByUriAddress(page.getUriAddress());
		
		if(pageCharacteristics == null)
			throw new PageCharacteristicsNotExistsException(page.getUriAddress());
		
		return pageCharacteristicsDao.updatePageCharacteristics(pageCharacteristics);
	}

	@Override
	public PageCharacteristics getPageCharacteristicsByUriAddress(String urlAddress) throws PageCharacteristicsNotExistsException
	{
		PageCharacteristics pageCharacteristics = pageCharacteristicsDao.findPageCharacteristicsByUriAddress(urlAddress);
		
		if(pageCharacteristics == null)
			throw new PageCharacteristicsNotExistsException(urlAddress);
		
		return pageCharacteristics;
	}

	@Override
	public boolean isPageCharacteristicsWithUriAddress(String urlAddress)
	{
		return pageCharacteristicsDao.findPageCharacteristicsByUriAddress(urlAddress) != null;
	}

	@Override
	public PageCharacteristics saveOrUpdatePageCharacteristics(PageCharacteristics page)
	{
		PageCharacteristics pageCharacteristics = pageCharacteristicsDao.findPageCharacteristicsByUriAddress(page.getUriAddress());
		
		if(pageCharacteristics != null)
		{			
			pageCharacteristics.setAdditionalCSS(page.getAdditionalCSS());
			return pageCharacteristicsDao.updatePageCharacteristics(pageCharacteristics);
		}		
		return pageCharacteristicsDao.updatePageCharacteristics(page);
	}
}
