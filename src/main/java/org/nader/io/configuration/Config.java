package org.nader.io.configuration;

import org.nader.io.interceptor.ThymeleafLayoutInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {

	@Autowired
	private ThymeleafLayoutInterceptor theInterceptor;
	
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(theInterceptor);
	}
}

