package com.technicaltest.springboot.auth.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.NullSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import com.technicaltest.springboot.auth.commons.Constants;
import com.technicaltest.springboot.auth.repository.UserRepository;
import com.technicaltest.springboot.auth.utils.EnvironmentHelper;

import lombok.RequiredArgsConstructor;

/**
 * 
 * @author edenilson
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.technicaltest.springboot.auth")
@EntityScan("com.technicaltest.springboot.auth.model")
@RequiredArgsConstructor
public class ApplicationConfiguration extends EnvironmentHelper{
	
	private final UserRepository userRepository;

    @Bean
    UserDetailsService userDetailsService() {
		return username -> userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format(getEnv().getProperty(Constants.NOTFOUND_MESSAGE), 
						username)));
	}
    
    @Bean
    AuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    	daoAuthenticationProvider.setUserDetailsService(this.userDetailsService());
    	daoAuthenticationProvider.setPasswordEncoder(this.customPasswordEncoder());
    	return daoAuthenticationProvider;
    }

    @Bean
    AuthenticationManager authManager(AuthenticationConfiguration configuration) throws Exception {
    	return configuration.getAuthenticationManager();
    }
    
    
    @Bean
    PasswordEncoder customPasswordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @Bean
    SecurityContextRepository securityContextRepository(){
        return new NullSecurityContextRepository(); // I use Null Repository since I don't need it for anything except store information in UserDetails
    }
}
