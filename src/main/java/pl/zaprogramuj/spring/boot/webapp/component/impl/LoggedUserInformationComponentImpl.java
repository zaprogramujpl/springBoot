package pl.zaprogramuj.spring.boot.webapp.component.impl;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import pl.zaprogramuj.spring.boot.webapp.component.LoggedUserInformationComponent;
import pl.zaprogramuj.spring.boot.webapp.domain.user.User;
import pl.zaprogramuj.spring.boot.webapp.excepotion.user.UserNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.service.UserService;
import pl.zaprogramuj.spring.boot.webapp.util.Constants;

@Component
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class LoggedUserInformationComponentImpl implements LoggedUserInformationComponent {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggedUserInformationComponentImpl.class);

	@Autowired
	private UserService userService;

	@Override
	public String tryGedLoggedUserName() {
		org.springframework.security.core.userdetails.User userProfile = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return userProfile.getUsername();
	}

	@Override
	public boolean isLoggedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName().equals(Constants.DEFALT_SPRING_ANONYMOUS_USER_NAME) ? false : true;
	}

	@Override
	public boolean userHasRole(String role) {
		Set<String> roles = new HashSet<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		auth.getAuthorities().stream().map(Object::toString).forEach(roles::add);
		return roles.contains(role);
	}

	@Override
	public User tryGetUserByEmailAddress(String emailAddress) {
		User user = null;
		try {
			user = userService.getUserByEmail(emailAddress);
		} catch (UserNotFoundException e) {
			LOGGER.error(e.getMessage());
		}
		return user;
	}

	@Override
	public User tryGetLoggedUser() {
		User user = null;
		try {
			user = userService.getUserByName(tryGedLoggedUserName());
		} catch (UserNotFoundException e) {
			LOGGER.error(e.getMessage());
		}
		return user;
	}

}
