package pl.zaprogramuj.spring.boot.webapp.webapp.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pl.zaprogramuj.spring.boot.webapp.component.LoggedUserInformationComponent;
import pl.zaprogramuj.spring.boot.webapp.component.impl.LoggedUserInformationComponentImpl;
import pl.zaprogramuj.spring.boot.webapp.dao.PostDao;
import pl.zaprogramuj.spring.boot.webapp.dao.UserDao;
import pl.zaprogramuj.spring.boot.webapp.service.PostService;
import pl.zaprogramuj.spring.boot.webapp.service.UserService;
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
	public LoggedUserInformationComponent getLoggedUserInformationComponent()
	{
		return new LoggedUserInformationComponentImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	//Mock Beans
	@Bean
	public UserDao mockUserDao()
	{
		return Mockito.mock(UserDao.class);
	}
	
	@Bean PostDao mockPostDao()
	{
		return Mockito.mock(PostDao.class);
	}


}
