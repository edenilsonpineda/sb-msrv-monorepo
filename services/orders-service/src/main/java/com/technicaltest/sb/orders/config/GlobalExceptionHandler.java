package com.technicaltest.sb.orders.config;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * A global exception handler for the REST API.
 * This adheres to the RFC 7807 standard
 * 
 * @author edenilson
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

}
