package com.technicaltest.springboot.auth.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technicaltest.springboot.auth.model.security.AuthRequest;
import com.technicaltest.springboot.auth.model.security.AuthResponse;
import com.technicaltest.springboot.auth.model.security.UserRegistrationRequest;
import com.technicaltest.springboot.auth.service.interfaces.IAuthService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private IAuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody UserRegistrationRequest request){
		try {
			authService.register(request);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(authService.register(request));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request){
		
		return ResponseEntity.ok(authService.authenticate(request));
	}
	
}
