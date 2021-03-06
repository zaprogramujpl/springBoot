package pl.zaprogramuj.spring.boot.webapp.webapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.zaprogramuj.spring.boot.webapp.dao.CommentDao;
import pl.zaprogramuj.spring.boot.webapp.dao.PageCharacteristicsDao;
import pl.zaprogramuj.spring.boot.webapp.dao.PostDao;
import pl.zaprogramuj.spring.boot.webapp.dao.UserDao;
import pl.zaprogramuj.spring.boot.webapp.dao.impl.CommentDaoImpl;
import pl.zaprogramuj.spring.boot.webapp.dao.impl.PageCharacteristicsDaoImpl;
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
    
    @Bean
    public PageCharacteristicsDao getPageCharacteristicsDao()
    {
    	return new PageCharacteristicsDaoImpl();
    }
    
    @Bean
    public CommentDao getCommentDao()
    {
    	return new CommentDaoImpl();
    }
}
