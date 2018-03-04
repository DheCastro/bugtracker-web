package br.com.triadworks.bugtracker.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
        auth.inMemoryAuthentication()
        		.withUser("marilia").password("123")
        			.roles("COMUM");
    }
	
}
