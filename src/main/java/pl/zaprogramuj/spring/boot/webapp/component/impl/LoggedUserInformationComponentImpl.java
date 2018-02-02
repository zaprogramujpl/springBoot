package pl.zaprogramuj.spring.boot.webapp.component.impl;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.zaprogramuj.spring.boot.webapp.component.LoggedUserInformationComponent;

@Component
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class LoggedUserInformationComponentImpl implements LoggedUserInformationComponent {

    @Override
    public String tryGedLoggedUserName() {
        org.springframework.security.core.userdetails.User userProfile = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userProfile.getUsername();
    }
}
