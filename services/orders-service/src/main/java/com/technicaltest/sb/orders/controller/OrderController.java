package com.technicaltest.sb.orders.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.technicaltest.sb.orders.exception.UnauthorizedException;
import com.technicaltest.sb.orders.model.dto.OrderRequestDto;
import com.technicaltest.sb.orders.service.interfaces.IOrderService;
import com.technicaltest.sb.orders.service.interfaces.JwtService;
import com.technicaltest.sb.orders.utils.AppServiceException;
import com.technicaltest.sb.orders.utils.HttpUtils;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController("/orders")
public class OrderController {
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping
	public ResponseEntity<?> createOrder(@RequestHeader HttpHeaders headers,
			@Valid @RequestBody OrderRequestDto orderRequest){
		Map<String, String> httpHeaders = headers.toSingleValueMap();
		String token = HttpUtils.getAuthorizationHeader(httpHeaders);
		
		if(token.isBlank()) {
			log.warn("Token provided is blank or empty");
			throw new UnauthorizedException();
		}
		
		if(!jwtService.isTokenValid(token)) {
			log.warn("Token is expired");
			throw new AppServiceException("Invalid JWT token");
		}
		return ResponseEntity.ok(orderService.addOrder(orderRequest));
	}
}
