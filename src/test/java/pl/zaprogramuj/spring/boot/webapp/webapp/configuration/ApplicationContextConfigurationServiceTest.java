package pl.zaprogramuj.spring.boot.webapp.webapp.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pl.zaprogramuj.spring.boot.webapp.webapp.repository.UserRepository;
import pl.zaprogramuj.spring.boot.webapp.webapp.repository.impl.UserRepositoryImpl;
import pl.zaprogramuj.spring.boot.webapp.webapp.service.UserService;
import pl.zaprogramuj.spring.boot.webapp.webapp.service.impl.UserServiceImpl;

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
	public UserRepository mockUserDao(){
		return Mockito.mock(UserRepository.class);
	}
}
