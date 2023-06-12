package com.technicaltest.sb.orders.exception;

import lombok.NoArgsConstructor;

/**
 * 
 * @author edenilson
 * @version 1.0
 * @since 1.0
 */

@NoArgsConstructor
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BadRequestException(String message) {
		super(message);
	}

}
