package com.technicaltest.springboot.auth.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technicaltest.springboot.auth.commons.Constants;
import com.technicaltest.springboot.auth.model.security.AuthRequest;
import com.technicaltest.springboot.auth.model.security.AuthResponse;
import com.technicaltest.springboot.auth.model.security.UserRegistrationRequest;
import com.technicaltest.springboot.auth.service.interfaces.IAuthService;
import com.technicaltest.springboot.auth.utils.web.AppServiceException;
import com.technicaltest.springboot.auth.utils.web.ResponseEntityUtil;
import com.technicaltest.springboot.auth.utils.web.UserAlreadyExistsException;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private IAuthService authService;

	@Autowired
	private ResponseEntityUtil responseEntityUtil;

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserRegistrationRequest request) {
		if (authService.existsByEmail(request.getEmail())) {
			log.warn(Constants.USERNAME_OR_EMAIL_EXISTS);
			throw new UserAlreadyExistsException(Constants.USERNAME_OR_EMAIL_EXISTS);
		}

		AuthResponse authResponse = authService.register(request);
		if (Objects.isNull(authResponse)) {
			log.error(Constants.USER_CANNOT_BE_CREATED);
			throw new AppServiceException(HttpStatus.CONFLICT.toString(), Constants.USER_CANNOT_BE_CREATED);
		}

		return responseEntityUtil.createCustomizedResponse(authResponse, HttpStatus.CREATED.value(),
				HttpStatus.CREATED.toString(), null);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
		AuthResponse user = authService.authenticate(request);

		if (Objects.isNull(user)) {
			log.error(Constants.USER_NOT_FOUND_NOR_EXISTS);
			throw new AppServiceException(Constants.USER_NOT_FOUND_NOR_EXISTS);
		}

		return responseEntityUtil.createOkResponse(user, HttpStatus.OK.toString(), "");
	}

}
