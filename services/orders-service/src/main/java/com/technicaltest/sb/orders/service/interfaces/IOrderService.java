package com.technicaltest.sb.orders.service.interfaces;

import com.technicaltest.sb.orders.model.dto.OrderRequestDto;
import com.technicaltest.sb.orders.model.dto.OrderResponseDto;

public interface IOrderService {
	public OrderResponseDto addOrder(OrderRequestDto orderRequest);
}
