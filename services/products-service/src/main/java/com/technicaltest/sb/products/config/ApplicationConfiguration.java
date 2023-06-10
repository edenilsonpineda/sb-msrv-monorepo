package com.technicaltest.sb.products.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 
 * @author edenilson
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class ApplicationConfiguration {
	
	
	/**
	 * Configures the CORS filter, allowing any origin/header and specific HTTP methods
	 * @return {@link CorsFilter}
	 */
	
	@Bean
	CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfig = new CorsConfiguration();
		
		corsConfig.setAllowCredentials(false);
		corsConfig.addAllowedOrigin("*");
		corsConfig.addAllowedHeader("*");
		
		corsConfig.addAllowedMethod(HttpMethod.GET);
		corsConfig.addAllowedMethod(HttpMethod.POST);
		corsConfig.addAllowedMethod(HttpMethod.PUT);
		corsConfig.addAllowedMethod(HttpMethod.DELETE);
		corsConfig.addAllowedMethod(HttpMethod.HEAD);
		corsConfig.addAllowedMethod(HttpMethod.OPTIONS);
		
		configurationSource.registerCorsConfiguration("/**", corsConfig);
		
		return new CorsFilter(configurationSource);
	}
}
