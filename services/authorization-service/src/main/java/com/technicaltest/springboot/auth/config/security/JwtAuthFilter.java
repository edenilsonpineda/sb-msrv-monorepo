package com.technicaltest.springboot.auth.config.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.technicaltest.springboot.auth.commons.Constants;
import com.technicaltest.springboot.auth.model.security.User;
import com.technicaltest.springboot.auth.repository.TokenRepository;
import com.technicaltest.springboot.auth.repository.UserRepository;
import com.technicaltest.springboot.auth.service.JwtServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
	private final JwtServiceImpl jwtService;
	private final UserDetailsService userDetailsService;
	private final TokenRepository tokenRepository;
	private final UserRepository userRepository;
	
	@Autowired
	private SecurityContextRepository securityContextRepository;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {
		if (request.getServletPath().contains("/auth")) {
			filterChain.doFilter(request, response);
			return;
		}
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		jwt = getJwtFromRequest(request);
		userEmail = jwtService.extractUsername(jwt);

		
		if (userEmail != null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
			
			boolean isTokenValid = tokenRepository.findByUserEmailAndTokenValue(userEmail, jwt).isPresent();
			
			
			if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
				log.info("Checking authentication");
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(authToken);
				SecurityContextHolder.setContext(securityContext);
				
				securityContextRepository.saveContext(securityContext, request, response);
			
			}
		}
		filterChain.doFilter(request, response);
	}
	
	
	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerTokenString = request.getHeader(Constants.AUTH_HEADER);
	
		return bearerTokenString != null ? bearerTokenString.substring(7) : null;
		
	}
}
