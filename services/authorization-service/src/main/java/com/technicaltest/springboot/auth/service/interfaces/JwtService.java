package com.technicaltest.springboot.auth.service.interfaces;

import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

/**
 * 
 * @author edenilson
 * @version 1.0
 * @since 1.0
 */
public interface JwtService {
	
	/**
	 * Extracts the user from the JWT Token claim
	 * @param token
	 * @return {@code String} the username from the claim
	 */
	String extractUsername(String token);
	
	/**
	 * 
	 * @param <T>
	 * @param token
	 * @param claimsResolver
	 * @return all the claims from token
	 */
	<T> T extractClaim(String token, Function<Claims, T> claimsResolver);
	
	
	/**
	 * 
	 * @param userDetails
	 * @return {@code String token} a new token with the user details as claim
	 */
	String generateToken(UserDetails userDetails);
	
	/**
	 * 
	 * @param username as string
	 * @return the new jwt token
	 */
	String generateToken(String username);
	
	/**
	 * Generate a new token with extra claims 
	 * @param extraClaims
	 * @param userDetails
	 * @return {@code String token}
	 */
	String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);
	
	/**
	 * 
	 * @param userDetails
	 * @return a new refresh token
	 */
	String generateRefreshToken(UserDetails userDetails);
	
	/**
	 * Checks if token is still valid
	 * @param token
	 * @param userDetails
	 * @return
	 */
	boolean isTokenValid(String token, UserDetails userDetails);
}
