package com.technicaltest.sb.products.utils.web;

import java.util.Map;

import com.technicaltest.sb.products.commons.Constants;

public final class HttpUtils {
	private HttpUtils(){}
	
	public static String getAuthorizationHeader(Map<String, String> headers) {
		return headers.getOrDefault(Constants.AUTHORIZATION_HEADER, "");
	}
}
