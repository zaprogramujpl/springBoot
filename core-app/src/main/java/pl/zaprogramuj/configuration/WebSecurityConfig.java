package pl.zaprogramuj.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private static final String[] MATCHERS = {"/", "/resources/**", "/registration", 
			  "/login*", "/webjars/**", "/css/**", "/forgot-password**"
			  ,"/reset-password**", "/admin**"};
	
  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http
      		.csrf().disable()
              .authorizeRequests()
                  .antMatchers(MATCHERS).permitAll()
                  .anyRequest().authenticated()
                  .and()
              .formLogin()
                  .loginPage("/login")
                  .usernameParameter("userLogin").passwordParameter("password")
                  .defaultSuccessUrl("/")
                  .permitAll()
                  .and()
              .logout()
              	.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                  .permitAll();
  }
}
