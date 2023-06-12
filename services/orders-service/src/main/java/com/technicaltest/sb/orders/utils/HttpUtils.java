package com.technicaltest.sb.orders.utils;

import java.util.Map;

import com.technicaltest.sb.orders.commons.Constants;

public final class HttpUtils {
	private HttpUtils(){}
	
	public static String getAuthorizationHeader(Map<String, String> headers) {
		String tokenToReturn = headers.getOrDefault(Constants.AUTHORIZATION_HEADER, "");
		tokenToReturn = !tokenToReturn.isBlank() ? tokenToReturn.split(" ")[1].trim() : "";
		
		return tokenToReturn;
	}
	
}
