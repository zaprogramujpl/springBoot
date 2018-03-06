package pl.zaprogramuj.spring.boot.webapp.service;

import pl.zaprogramuj.spring.boot.webapp.domain.page.PageCharacteristics;
import pl.zaprogramuj.spring.boot.webapp.excepotion.pagecharacteristics.PageCharacteristicsExistsException;
import pl.zaprogramuj.spring.boot.webapp.excepotion.pagecharacteristics.PageCharacteristicsNotExistsException;

public interface PageCharacteristicsService
{
	public abstract void addPageCharacteristics(PageCharacteristics page) throws PageCharacteristicsExistsException;
	public abstract PageCharacteristics updatePageCharacteristics(PageCharacteristics page) throws PageCharacteristicsNotExistsException;
	public abstract PageCharacteristics saveOrUpdatePageCharacteristics(PageCharacteristics page);
	public abstract PageCharacteristics getPageCharacteristicsByUriAddress(String uriAddress) throws PageCharacteristicsNotExistsException;
	public abstract boolean isPageCharacteristicsWithUriAddress(String uriAddress);
}
