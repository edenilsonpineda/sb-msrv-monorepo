package com.technicaltest.springboot.auth.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.Getter;

/**
 * 
 * @author edenilson
 *
 */
@Component
public abstract class EnvironmentHelper {
	

	@Autowired
	@Getter
	private Environment env;
}
