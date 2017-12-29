package pl.zaprogramuj.spring.boot.webapp.webapp.util;

import java.security.SecureRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

	private static final String SALT = "salt";
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
	}
}
