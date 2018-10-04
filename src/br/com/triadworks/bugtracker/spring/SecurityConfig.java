package br.com.triadworks.bugtracker.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
			.antMatchers("/pages/usuarios.xhtml").hasRole("ADMIN")
			.regexMatchers(HttpMethod.GET, "/pages/bugs.xhtml\\?id=[\\d]+").permitAll()
			.antMatchers("/pages/bugs.xhtml").hasAnyAuthority("ROLE_ADMIN", "ROLE_USUARIO")
			.antMatchers("/pages/dashboard.xhtml").permitAll()
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
        		.passwordEncoder(passwordEncoder());
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
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
