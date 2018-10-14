package br.com.triadworks.bugtracker.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
	private UserDetailsService users;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
        		.userDetailsService(this.users)
        		.passwordEncoder(new BCryptPasswordEncoder());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// desabilita Cross-Site Request Forgery
	    http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/javax.faces.resource/**").permitAll()
			.antMatchers("/pages/usuarios.xhtml").hasRole("ADMIN")
			.antMatchers("/pages/bugs.xhtml").hasAnyRole("ADMIN", "USUARIO")
			.anyRequest().authenticated();
		
		// login
		http.formLogin()
    			.loginPage("/login.xhtml").permitAll()
    			.failureUrl("/login.xhtml?error=true")
    			.defaultSuccessUrl("/pages/bugs.xhtml", true)
    			;
		
		// logout
		http.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login.xhtml?logout").permitAll()
			;
		
	}
	
}
