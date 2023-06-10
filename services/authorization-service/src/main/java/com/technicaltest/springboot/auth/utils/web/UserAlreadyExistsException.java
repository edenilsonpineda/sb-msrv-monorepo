package com.technicaltest.springboot.auth.utils.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author edenilson
 * 
 * @version 1.0
 *
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1025807041936570553L;

	/**
	 * Constructs a new runtime exception with the specified detail message. The
	 * cause is not initialized, and may subsequently be initialized by a call to
	 * {@link #initCause}.
	 *
	 * @param message the detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
	 */
	public UserAlreadyExistsException(String message) {
		super(message);
	}
	
}
