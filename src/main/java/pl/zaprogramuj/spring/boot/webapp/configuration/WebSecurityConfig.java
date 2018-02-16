package pl.zaprogramuj.spring.boot.webapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import pl.zaprogramuj.spring.boot.webapp.domain.user.UserRoleEnum;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private static final String[] MATCHERS = {"/", "/resources/**", "/registration", 
											  "/login*", "/webjars/**", "/css/**" , 
											  "/js/**", "/img/**", "/forgot-password**"
											  ,"/reset-password**", "/post**", "/post/**"};

	private static final String[] MATCHERS_ADMIN = { "/admin**", "/admin/**"};
	
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        		.csrf().disable()
                .authorizeRequests()
                    .antMatchers(MATCHERS).permitAll()
                    .antMatchers(MATCHERS_ADMIN).hasRole(UserRoleEnum.ADMIN.toString())
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

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}