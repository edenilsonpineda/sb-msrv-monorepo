package com.technicaltest.springboot.auth.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.context.NullSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import com.technicaltest.springboot.auth.commons.Constants;
import com.technicaltest.springboot.auth.model.security.Permission;
import com.technicaltest.springboot.auth.model.security.Role;
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
public class DefaultSecurityConfig extends EnvironmentHelper{
	
	private final JwtAuthFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	private final LogoutHandler logoutHandler;
	
	
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
		.cors(cors -> cors.disable())
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(authz -> {
				authz
					.requestMatchers(getEnv().getProperty(Constants.API_AUTH_URL_MAPPING))
					.permitAll();
				authz
					.requestMatchers(getEnv().getProperty(Constants.API_AUTH_ROOT_URL_MAPPING))
					.permitAll();
				
				authz.anyRequest().fullyAuthenticated();
				
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
