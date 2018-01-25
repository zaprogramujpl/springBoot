package pl.zaprogramuj.spring.boot.webapp.webapp.component.impl;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pl.zaprogramuj.spring.boot.webapp.component.LoggedUserInformationComponent;
import pl.zaprogramuj.spring.boot.webapp.webapp.configuration.ApplicationContextConfigurationServiceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ApplicationContextConfigurationServiceTest.class })
public class LoggedUserInformationComponentImplTest {

    @Autowired
    private LoggedUserInformationComponent loggedUserInformactionComponent;

    @Test
    @WithMockUser(username = "Test")
    public void shouldReturnUsername()
    {
        assertEquals("Test", loggedUserInformactionComponent.tryGedLoggedUserName());
    }

    @Test(expected = NullPointerException.class)
    public void shouldReturnNull()
    {
        loggedUserInformactionComponent.tryGedLoggedUserName();
    }
}
