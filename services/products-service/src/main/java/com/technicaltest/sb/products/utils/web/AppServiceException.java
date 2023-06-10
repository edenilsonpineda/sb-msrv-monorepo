package com.technicaltest.sb.products.utils.web;

import lombok.Getter;

/**
 *
 * This class is used to throw a custom exception when a exception is handled 
 * instead of a full StackTrace
 * 
 * @author edenilsonpineda
 * @version 1.0
 * @since 1.0
 * 
 */

@Getter
public class AppServiceException extends RuntimeException {

	private static final long serialVersionUID = 4217841775839040673L;
	private final String code;
	
	/**
	 * Instantiates a new AppServiceException with the HttpStatusCode and
	 * the custom message
	 * 
	 * @param code HTTP code
	 * @param message the custom message to be sent
	 */
	public AppServiceException(String code, String message) {
		super(message);
		this.code = code;
	}
	
	/**
	 * 
	 * Instantiates a new AppServiceException with the message only.
	 * @param message
	 */
	public AppServiceException(String message) {
		super(message);
		this.code = "";
	}

}
