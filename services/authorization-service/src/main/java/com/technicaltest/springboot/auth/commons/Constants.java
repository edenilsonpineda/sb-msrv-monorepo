package com.technicaltest.springboot.auth.commons;

/**
 * 
 * @author edenilson
 *
 */
public final class Constants {
	private Constants() {} // Since is a final class, no instantiation is needed. 
	
	public static final String NOTFOUND_MESSAGE = "app.constants.messages.notfound";
	public static final String SECURED_PATH = "app.constants.secured-path";
	public static final String API_AUTH_ROOT_URL_MAPPING = "app.constants.api.authroot-url";
	public static final String API_AUTH_URL_MAPPING = "app.constants.api.auth-url";
	public static final String API_ROOT_URL_PATH = "app.constants.api.root-path";
	public static final String BEARER_PREFIX = "Bearer ";
}
