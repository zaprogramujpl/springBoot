package pl.zaprogramuj.spring.boot.webapp.webapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.zaprogramuj.spring.boot.webapp.dao.PostDao;
import pl.zaprogramuj.spring.boot.webapp.dao.UserDao;
import pl.zaprogramuj.spring.boot.webapp.dao.impl.PostDaoImpl;
import pl.zaprogramuj.spring.boot.webapp.dao.impl.UserDaoImpl;

@Configuration
public class ApplicationContextConfigurationDaoTest {

    @Bean
    public UserDao getUserDao(){
        return new UserDaoImpl();
    }
    
    @Bean
    public PostDao getPostDao(){
    	return new PostDaoImpl();
    }
}
