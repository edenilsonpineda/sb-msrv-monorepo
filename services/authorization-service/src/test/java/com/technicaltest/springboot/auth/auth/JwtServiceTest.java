package com.technicaltest.springboot.auth.auth;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;

import com.technicaltest.springboot.auth.TestUtils;
import com.technicaltest.springboot.auth.model.security.User;
import com.technicaltest.springboot.auth.service.JwtServiceImpl;
import com.technicaltest.springboot.auth.service.interfaces.JwtService;
import com.technicaltest.springboot.auth.utils.JwtUtils;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JwtServiceTest extends TestUtils{
	
	private transient JwtService jwtService;
	private transient User user;
	
	@BeforeAll
	void beforeAll() {
		String secretKey = JwtUtils.generateSecretKey();
		jwtService = new JwtServiceImpl(secretKey);
		
	}

	@Test
	void generateJwtTokenAndThenValidate(TestInfo testInfo) throws InterruptedException {
		user = User
				.builder()
				.firstName(FAKER.name().firstName())
				.lastName(FAKER.name().lastName())
				.username(testInfo.getDisplayName())
				.email(testInfo.getDisplayName())
				.password(FAKER.internet().password()).build();
				
		String jwtToken = jwtService.generateToken(user);
		
		Assertions.assertTrue(jwtService.isTokenValid(jwtToken, user));
		
	}

	@Test
	void generateJwtTokenWithNullValuesThrowsException() {
		assertThrows(NullPointerException.class, () -> jwtService.generateToken(user));
	}
}
