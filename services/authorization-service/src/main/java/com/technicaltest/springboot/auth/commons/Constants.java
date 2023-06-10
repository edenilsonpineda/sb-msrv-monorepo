package com.technicaltest.springboot.auth.commons;

/**
 * 
 * @author edenilson
 *
 */
public final class Constants {
	private Constants() {} // Since is a final class, no instantiation is needed. 
	
	public static final String NOTFOUND_MESSAGE = "app.constants.messages.notfound";
	public static final String SECURED_PATH = "app.constants.api.secured-path";
	public static final String API_AUTH_ROOT_URL_MAPPING = "app.constants.api.authroot-url";
	public static final String API_AUTH_URL_MAPPING = "app.constants.api.auth-url";
	public static final String API_ROOT_URL_PATH = "app.constants.api.root-path";
	public static final String BEARER_PREFIX = "Bearer ";
	public static final String AUTH_HEADER = "Authorization";
	
	
	// CUSTOM MESSAGES
	public static final String USERNAME_OR_EMAIL_EXISTS = "Username already registered! Try again with another one.";
	public static final String USER_CANNOT_BE_CREATED = "User cannot be created.";
	public static final String USER_NOT_FOUND_NOR_EXISTS = "User cannot be found.";
	public static final String INVALID_CREDENTIALS = "Invalid credentials.";
	
	public static final String GENERIC_ERROR_MESSAGE = "Something went wrong while trying to process the request.";
}
