package pl.zaprogramuj.spring.boot.webapp.webapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.zaprogramuj.spring.boot.webapp.webapp.domain.authority.UserRole;
import pl.zaprogramuj.spring.boot.webapp.webapp.domain.user.UserProfile;
import pl.zaprogramuj.spring.boot.webapp.webapp.repository.UserRepository;

@Service
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UserProfile userProfile = userRepository.findByLogin(login);
		if(null == userProfile)
		{
			throw new UsernameNotFoundException("Username not found");
		}	
		return new org.springframework.security.core.userdetails.User(userProfile.getLogin(), userProfile.getPassword(), getGrantedAuthorities(userProfile));
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(UserProfile userProfile)
	{
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for(UserRole role : userProfile.getUserRoles())
		{
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
		}		
		return authorities;
	}


}
