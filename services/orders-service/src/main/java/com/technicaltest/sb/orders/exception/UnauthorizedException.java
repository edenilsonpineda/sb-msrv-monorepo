package com.technicaltest.sb.orders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

/**
 * Use to handle unauthorized access when no JwtToken is provided on headers
 * 
 * @author edenilson
 * @version 1.0
 * @since 1.0
 *
 */
@NoArgsConstructor
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "You are not allowed to access this resource")
public class UnauthorizedException extends RuntimeException{

	private static final long serialVersionUID = -5009350581431984092L;
	
	/***
	 * Constructs a new runtime exception with the custom message.
	 * 
	 * @param message 
	 */
	public UnauthorizedException(String message) {
		super(message);
	}

}
