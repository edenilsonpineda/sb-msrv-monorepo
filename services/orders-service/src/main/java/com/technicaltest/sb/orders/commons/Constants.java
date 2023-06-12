package com.technicaltest.sb.orders.commons;

public final class Constants {
	private Constants() {}
	
	public static final String AUTHORIZATION_HEADER = "authorization";
	public static final String FAKE_API_URL = "${app.constants.api.consumer.fake-api-url}";
	public static final String INVALID_TOKEN_MESSAGE = "Invalid authorization token";
}
