package pl.zaprogramuj.spring.boot.webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.zaprogramuj.spring.boot.webapp.dao.UserDao;
import pl.zaprogramuj.spring.boot.webapp.domain.user.User;

@Service
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User userProfile = userRepository.findByLogin(login);
		if (null == userProfile) {
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(userProfile.getLogin(), userProfile.getPassword(),
				userProfile.getAuthorities());
	}
}
