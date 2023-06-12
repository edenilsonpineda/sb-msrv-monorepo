package com.technicaltest.sb.orders.model.helper;

import com.technicaltest.sb.orders.exception.BadRequestException;
import com.technicaltest.sb.orders.model.dto.OrderRequestDto;

/**
 * 
 * @author edenilson
 *
 */
public class OrderHelper {
	private OrderHelper() {}
	
	static void validateOrder(OrderRequestDto requestDto) {
		if(requestDto.getProducts().isEmpty()) {
			throw new BadRequestException("The order must have one or more items to be completed.");
		}
		
		requestDto.getProducts().forEach(product -> {
		});
	}
}
