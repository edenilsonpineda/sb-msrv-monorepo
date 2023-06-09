package com.technicaltest.springboot.auth.service.security;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technicaltest.springboot.auth.commons.Constants;
import com.technicaltest.springboot.auth.model.security.AuthRequest;
import com.technicaltest.springboot.auth.model.security.AuthResponse;
import com.technicaltest.springboot.auth.model.security.Role;
import com.technicaltest.springboot.auth.model.security.User;
import com.technicaltest.springboot.auth.model.security.UserRegistrationRequest;
import com.technicaltest.springboot.auth.model.token.Token;
import com.technicaltest.springboot.auth.repository.TokenRepository;
import com.technicaltest.springboot.auth.repository.UserRepository;
import com.technicaltest.springboot.auth.service.JwtServiceImpl;
import com.technicaltest.springboot.auth.service.interfaces.IAuthService;
import com.technicaltest.springboot.auth.utils.web.AppServiceException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthService {
	
	private final UserRepository userRepository;
	private final TokenRepository tokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtServiceImpl jwtService;
	private final AuthenticationManager authManager;
	
	
	public AuthResponse authenticate(AuthRequest request) {
		AuthResponse authResponse = null;
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(
						request.getEmail(), 
						request.getPassword()));
			
			UserDetails user = userRepository.findByEmail(request.getEmail())
					.orElseThrow();
			
			
			String jwtToken = jwtService.generateToken(user);
			String refreshToken = jwtService.generateRefreshToken(user);
			
			authResponse = AuthResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
			
			log.debug("AuthResponseObject: {}", authResponse);

		} catch (NoSuchElementException ex) {
			log.error("Something went wrong while trying to authenticate the User", ex);
		} catch (BadCredentialsException e) {
			log.error(e.getMessage());
			throw new com.technicaltest.springboot.auth.utils.web.BadCredentialsException(Constants.INVALID_CREDENTIALS);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new AppServiceException(Constants.GENERIC_ERROR_MESSAGE);
		}
		
		return authResponse;
	}
	
	
	public void saveUserToken(User user, String jwtToken) {
		try {
			Token token = Token.builder()
					.user(user)
					.tokenValue(jwtToken)
					.expired(false)
					.revoked(false)
					.build();
			
			tokenRepository.save(token);
		} catch (Exception ex) {
			log.error("Something went wrong while trying to save the User's token", ex);
		}
	}

	@Override
	public AuthResponse register(UserRegistrationRequest userRegistrarRequest) {
		AuthResponse authResponse = null;
		try {
			User newUserDetails = User.builder()
					.firstName(userRegistrarRequest.getFirstName())
					.lastName(userRegistrarRequest.getLastName())
					.email(userRegistrarRequest.getEmail())
					.password(passwordEncoder.encode(userRegistrarRequest.getPassword()))
					.role(Role.CUSTOMER)
					.build();
			
			User savedUser = userRepository.save(newUserDetails);
			String newJwtToken = jwtService.generateToken(newUserDetails);
			String refreshToken = jwtService.generateRefreshToken(newUserDetails);
			
			saveUserToken(savedUser, newJwtToken);
			
			authResponse = AuthResponse.builder()
					.accessToken(newJwtToken)
					.refreshToken(refreshToken)
					.build();
			
		} catch (Exception e) {
			log.error("Something went wrong while trying to register the new user {}, due to: {}", userRegistrarRequest.getFirstName(), e.getMessage());
		}
		
		return authResponse;
	}


	@Override
	public void revokeAllUserTokens(User user) {
		try {
			List<Token> validTokens = tokenRepository.findAllValidTokenByUser(user.getId());
			if(validTokens.isEmpty()) return; 
			
			validTokens.forEach(token -> {token.setExpired(true); token.setRevoked(true);}); 
			
			tokenRepository.saveAll(validTokens);
		} catch (Exception e) {
			log.error("Something went wrong while trying to revoke user's tokens", e.getMessage());
		}
	}


	@Override
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
		try {
			final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		    final String refreshToken;
		    final String userEmail;
		    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
		      return;
		    }
		    refreshToken = authHeader.substring(7);
		    userEmail = jwtService.extractUsername(refreshToken);
		    if (userEmail != null) {
		      User user = this.userRepository.findByEmail(userEmail)
		              .orElseThrow();
		      if (jwtService.isTokenValid(refreshToken, user)) {
		        String accessToken = jwtService.generateToken(user);
		        revokeAllUserTokens(user);
		        saveUserToken(user, accessToken);
		        AuthResponse authResponse = AuthResponse.builder()
		                .accessToken(accessToken)
		                .refreshToken(refreshToken)
		                .build();
		        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
		      }
		    }
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}


	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
}
