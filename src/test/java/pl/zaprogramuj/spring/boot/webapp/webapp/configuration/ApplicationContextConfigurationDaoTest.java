package pl.zaprogramuj.spring.boot.webapp.webapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.zaprogramuj.spring.boot.webapp.repository.UserDao;
import pl.zaprogramuj.spring.boot.webapp.repository.impl.UserDaoImpl;

@Configuration
public class ApplicationContextConfigurationDaoTest {

    @Bean
    public UserDao mockUserDao(){
        return new UserDaoImpl();
    }
}
