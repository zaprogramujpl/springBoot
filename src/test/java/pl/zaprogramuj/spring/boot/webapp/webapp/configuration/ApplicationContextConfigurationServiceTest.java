package pl.zaprogramuj.spring.boot.webapp.webapp.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pl.zaprogramuj.spring.boot.webapp.component.LoggedUserInformationComponent;
import pl.zaprogramuj.spring.boot.webapp.component.impl.LoggedUserInformationComponentImpl;
import pl.zaprogramuj.spring.boot.webapp.dao.PageCharacteristicsDao;
import pl.zaprogramuj.spring.boot.webapp.dao.PostDao;
import pl.zaprogramuj.spring.boot.webapp.dao.UserDao;
import pl.zaprogramuj.spring.boot.webapp.service.PageCharacteristicsService;
import pl.zaprogramuj.spring.boot.webapp.service.PostService;
import pl.zaprogramuj.spring.boot.webapp.service.UserService;
import pl.zaprogramuj.spring.boot.webapp.service.impl.PageCharacteristicsServiceImpl;
import pl.zaprogramuj.spring.boot.webapp.service.impl.PostServiceImpl;
import pl.zaprogramuj.spring.boot.webapp.service.impl.UserServiceImpl;

@Configuration
public class ApplicationContextConfigurationServiceTest
{

	@Bean
	public UserService getUserService()
	{
		return new UserServiceImpl();
	}

	@Bean
	public PostService getPostService()
	{
		return new PostServiceImpl();
	}

	@Bean
	public PageCharacteristicsService getPageCharacteristics()
	{
		return new PageCharacteristicsServiceImpl();
	}

	@Bean
	public LoggedUserInformationComponent getLoggedUserInformationComponent()
	{
		return new LoggedUserInformationComponentImpl();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	// Mock Beans
	@Bean
	public UserDao mockUserDao()
	{
		return Mockito.mock(UserDao.class);
	}

	@Bean
	public PostDao mockPostDao()
	{
		return Mockito.mock(PostDao.class);
	}

	@Bean
	public PageCharacteristicsDao mockPageCharacteristicsDao()
	{
		return Mockito.mock(PageCharacteristicsDao.class);
	}
}
