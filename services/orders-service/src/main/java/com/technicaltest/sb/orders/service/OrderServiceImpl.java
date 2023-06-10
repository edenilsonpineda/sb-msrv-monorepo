package com.technicaltest.sb.orders.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technicaltest.sb.orders.model.Order;
import com.technicaltest.sb.orders.model.dto.OrderRequestDto;
import com.technicaltest.sb.orders.model.dto.OrderResponseDto;
import com.technicaltest.sb.orders.repository.OrderRepository;
import com.technicaltest.sb.orders.service.interfaces.IOrderService;
import com.technicaltest.sb.orders.service.interfaces.IProductsLocalFeignClientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements IOrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private IProductsLocalFeignClientService productsService;

	@Override
	public OrderResponseDto addOrder(OrderRequestDto orderRequest) {
		try {
			Order order = Order.builder()
					.orderCreatedDate(LocalDate.now()).build();
			
			this.orderRepository.save(order);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return null;
	}

}
