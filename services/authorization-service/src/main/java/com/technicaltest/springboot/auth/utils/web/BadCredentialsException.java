package com.technicaltest.springboot.auth.utils.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class BadCredentialsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new BadCredentialsException
	 *
	 * 
	 * @param code HTTP code
	 * @param message the custom message to be sent
	 */
	public BadCredentialsException(String message) {
		super(message);
	}

}
