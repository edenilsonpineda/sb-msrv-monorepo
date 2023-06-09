package com.technicaltest.springboot.auth.service.interfaces;

import com.technicaltest.springboot.auth.model.security.AuthRequest;
import com.technicaltest.springboot.auth.model.security.AuthResponse;
import com.technicaltest.springboot.auth.model.security.User;
import com.technicaltest.springboot.auth.model.security.UserRegistrationRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The AuthService interface is the contract for authorization operations.
 * 
 * @author edenilson
 * @version 1.0
 * @since 1.0
 *
 */
public interface IAuthService {

	/**
	 * Authenticates the user by their email
	 * 
	 * @param authRequest
	 * @return the authorization and refresh token
	 */
	AuthResponse authenticate(AuthRequest authRequest);

	/**
	 * Registers a new user
	 * 
	 * @param userRegistrarRequest the user object
	 * @return the authorization and refresh token
	 */
	AuthResponse register(UserRegistrationRequest userRegistrarRequest);

	/**
	 * Saves the user token when is expired or is not created
	 * 
	 * @param user data object
	 * @param the  JWT authorization token
	 * 
	 */
	void saveUserToken(User user, String jwtToken);

	/**
	 * Revokes all user tokens in case the expire time is reached.
	 * 
	 * @param user required to revoke authorization tokens
	 */
	void revokeAllUserTokens(User user);

	/**
	 * Checks if the token is valid and returns a new token in case the token is
	 * expired
	 * 
	 * @param request
	 * @param response
	 */
	void refreshToken(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Checks if the email already exists and enabled.
	 *
	 * @param email as username 
	 * @return <code>true</code> if username exists
	 */
	boolean existsByEmail(String email);
}
