package br.com.triadworks.bugtracker.spring;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class SpringWebApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// inicializa contexto do Spring
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppConfig.class);
		// registra ContextListener do Spring
		servletContext.addListener(new ContextLoaderListener(context));
		servletContext.addListener(new RequestContextListener());
		// registra filtros
		FilterRegistration filter = servletContext.addFilter("oemiv", new OpenEntityManagerInViewFilter());
		filter.addMappingForUrlPatterns(null, true, "/*");
	}

}
