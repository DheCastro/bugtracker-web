package br.com.triadworks.bugtracker.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// todas requisições devem ser autenticadas exceto para resources
		http.authorizeRequests()
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/javax.faces.resource/**").permitAll()
	        .anyRequest().authenticated();
		
		// login
		http.formLogin()
    			.loginPage("/login.xhtml").permitAll()
    			.failureUrl("/login.xhtml?error=true")
    			.defaultSuccessUrl("/pages/dashboard.xhtml", true)
    			;
		
		// desabilita Cross-Site Request Forgery
	    http.csrf().disable();
		
		// logout
		http.logout()
			.logoutSuccessUrl("/login.xhtml?logout").permitAll()
			;
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
        		.userDetailsService(userDetailsService)
        		.passwordEncoder(new BCryptPasswordEncoder());
    }
	
	/**
	 * Configura internacionalização para Spring Security
	 */
	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.addBasenames("classpath:org/springframework/security/messages");
	    return messageSource;
	}
	
}
