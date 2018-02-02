package pl.zaprogramuj.spring.boot.webapp.webapp.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pl.zaprogramuj.spring.boot.webapp.component.LoggedUserInformationComponent;
import pl.zaprogramuj.spring.boot.webapp.component.impl.LoggedUserInformationComponentImpl;
import pl.zaprogramuj.spring.boot.webapp.repository.UserDao;
import pl.zaprogramuj.spring.boot.webapp.service.UserService;
import pl.zaprogramuj.spring.boot.webapp.service.impl.UserServiceImpl;

@Configuration
public class ApplicationContextConfigurationServiceTest {
	
	@Bean(name = "userServiceTest")
	public UserService getUserService() {
		return new UserServiceImpl();
	}
	
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public UserDao mockUserDao(){
		return Mockito.mock(UserDao.class);
	}

	@Bean
	public LoggedUserInformationComponent getLoggedUserInformationComponent()
	{
		return new LoggedUserInformationComponentImpl();
	}
}
