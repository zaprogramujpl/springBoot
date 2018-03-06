package pl.zaprogramuj.spring.boot.webapp.dao;

import pl.zaprogramuj.spring.boot.webapp.domain.page.PageCharacteristics;

public interface PageCharacteristicsDao
{
	public abstract void addPageCharacteristics(PageCharacteristics page);
	public abstract PageCharacteristics updatePageCharacteristics(PageCharacteristics page);
	public abstract PageCharacteristics findPageCharacteristicsByUriAddress(String urlAddress);
}
