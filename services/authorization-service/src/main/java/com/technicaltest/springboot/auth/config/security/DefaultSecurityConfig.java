package com.technicaltest.springboot.auth.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.technicaltest.springboot.auth.commons.Constants;
import com.technicaltest.springboot.auth.utils.EnvironmentHelper;

import lombok.RequiredArgsConstructor;;

/**
 * 
 * @author edenilson
 *
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class DefaultSecurityConfig extends EnvironmentHelper{
	
	private final JwtAuthFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	private final LogoutHandler logoutHandler;
	
	
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.securityMatcher(getEnv().getProperty(Constants.API_ROOT_URL_PATH));
		
		httpSecurity
		.authorizeHttpRequests(authz -> {
				authz
					.requestMatchers(getEnv().getProperty(Constants.API_AUTH_URL_MAPPING))
					.permitAll();
				authz.requestMatchers(getEnv().getProperty(Constants.API_ROOT_URL_PATH));
				authz.anyRequest().authenticated();
			}
		)
		.sessionManagement(sessionManagement -> 
			sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		)
		.authenticationProvider(this.authenticationProvider)
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		.logout(logout -> logout.logoutUrl("/api/v1/demo/auth/logout")
				.addLogoutHandler(this.logoutHandler)
				.logoutSuccessHandler((request,response,authentication) -> SecurityContextHolder.clearContext()))
		;
		
		return httpSecurity.build();
	}
	
}
