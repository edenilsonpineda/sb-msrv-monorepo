package com.technicaltest.springboot.auth.service.interfaces;

import com.technicaltest.springboot.auth.model.security.AuthRequest;
import com.technicaltest.springboot.auth.model.security.AuthResponse;
import com.technicaltest.springboot.auth.model.security.User;
import com.technicaltest.springboot.auth.model.security.UserRegistrationRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IAuthService {
	AuthResponse authenticate(AuthRequest authRequest);
	AuthResponse register(UserRegistrationRequest userRegistrarRequest);
	void saveUserToken(User user, String jwtToken);
	void revokeAllUserTokens(User user);
	void refreshToken(HttpServletRequest request, HttpServletResponse response);
}
