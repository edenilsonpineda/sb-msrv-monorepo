package com.technicaltest.springboot.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author edenilson
 * @version 1.0
 *
 */


@RestController
@RequestMapping("/shop")
public class ShopController {
	
	@PreAuthorize("isAuthenticated() && hasRole(T(com.technicaltest.springboot.auth.model.security.CUSTOMER).ROLE_CUSTOMER)")
	@GetMapping("/hello")
	public String hello() {
		return "hello from secured endpoint";
	}
	
}
