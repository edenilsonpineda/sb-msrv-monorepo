package com.technicaltest.sb.orders.utils;

import java.util.Map;

import com.technicaltest.sb.orders.commons.Constants;

public final class HttpUtils {
	private HttpUtils(){}
	
	public static String getAuthorizationHeader(Map<String, String> headers) {
		return headers.getOrDefault(Constants.AUTHORIZATION_HEADER, "");
	}
}
