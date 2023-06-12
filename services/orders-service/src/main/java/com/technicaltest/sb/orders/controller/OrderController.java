package com.technicaltest.sb.orders.controller;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technicaltest.sb.orders.commons.Constants;
import com.technicaltest.sb.orders.exception.AppServiceException;
import com.technicaltest.sb.orders.exception.UnauthorizedException;
import com.technicaltest.sb.orders.model.User;
import com.technicaltest.sb.orders.model.dto.OrderRequestDto;
import com.technicaltest.sb.orders.repository.UserRepository;
import com.technicaltest.sb.orders.service.interfaces.IOrderService;
import com.technicaltest.sb.orders.service.interfaces.JwtService;
import com.technicaltest.sb.orders.utils.HttpUtils;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

	@PostMapping
	public ResponseEntity<?> placeOrder(@RequestHeader HttpHeaders headers,
			@Valid @RequestBody OrderRequestDto orderRequest) {
		
		Map<String, String> httpHeaders = headers.toSingleValueMap();
		String token = HttpUtils.getAuthorizationHeader(httpHeaders);

		if (token.isBlank()) {
			log.warn("Token provided is blank or empty");
			throw new UnauthorizedException(Constants.INVALID_TOKEN_MESSAGE.concat(". Try again."));
		}

		if (!jwtService.isTokenValid(token)) {
			log.warn("Token is expired");
			throw new AppServiceException(Constants.INVALID_TOKEN_MESSAGE);
		}
		
	
		
		String userName = jwtService.extractUsername(token);
		if (Objects.nonNull(userName) && !userName.isBlank()) {
			User user = userRepository.findByEmail(userName)
						.orElseThrow(() -> new UnauthorizedException(Constants.INVALID_TOKEN_MESSAGE));
			
			orderRequest.setUser(user);
		}
		

		return ResponseEntity.ok(orderService.addOrder(orderRequest));
		
		
	}
	
	@PostMapping("/process")
	public ResponseEntity<OrderRequestDto> payOrder(@RequestHeader HttpHeaders headers
			, @RequestParam("orderId") Long orderId){
		
		Map<String, String> httpHeaders = headers.toSingleValueMap();
		String token = HttpUtils.getAuthorizationHeader(httpHeaders);

		if (token.isBlank()) {
			log.warn("Token provided is blank or empty");
			throw new UnauthorizedException();
		}

		if (!jwtService.isTokenValid(token)) {
			log.warn("Token is expired");
			throw new AppServiceException(Constants.INVALID_TOKEN_MESSAGE);
		}
		
		
		return ResponseEntity.ok(null);
	}
}
