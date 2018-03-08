package pl.zaprogramuj.spring.boot.webapp.webapp.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import pl.zaprogramuj.spring.boot.webapp.dao.UserDao;
import pl.zaprogramuj.spring.boot.webapp.domain.user.User;
import pl.zaprogramuj.spring.boot.webapp.webapp.configuration.ApplicationContextConfigurationDaoTest;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(value = { ApplicationContextConfigurationDaoTest.class })
public class UserDaoImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserDao userDao;

    @Test
    public void shouldAssertNull()
    {
       User user = userDao.findByName("testUser");
       assertNull(user);
    }

    @Test
    public void shouldReturnUser()
    {
        User user = new User();
        user.setUserName("testUser");
        user.setEmailAddress("testEmail");

        entityManager.persist(user);

        User daoUser = userDao.findByName(user.getUsername());
        assertEquals(user.getUsername(), daoUser.getUsername());
    }
}

