package pl.zaprogramuj.spring.boot.webapp.component;

import pl.zaprogramuj.spring.boot.webapp.domain.user.User;

public interface LoggedUserInformationComponent {

    public abstract String tryGedLoggedUserName();
    public abstract boolean isLoggedUser();
    public abstract boolean userHasRole(String role);    
    public abstract User tryGetUserByEmailAddress(String emailAddress);
    public abstract User tryGetLoggedUser();
}
