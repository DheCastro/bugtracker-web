package br.com.triadworks.bugtracker.spring.scopes;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringViewScopeConfig {

	@Bean
	public static CustomScopeConfigurer customScopeConfigurer() {
		Map<String, Object> scopes = new HashMap<>();
		scopes.put("view", new SpringViewScope());

		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		configurer.setScopes(scopes);
		return configurer;
	}
}
