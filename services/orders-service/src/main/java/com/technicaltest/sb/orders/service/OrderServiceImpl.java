package com.technicaltest.sb.orders.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technicaltest.sb.orders.exception.AppServiceException;
import com.technicaltest.sb.orders.exception.BadRequestException;
import com.technicaltest.sb.orders.feign.client.IProductsClientService;
import com.technicaltest.sb.orders.model.Order;
import com.technicaltest.sb.orders.model.dto.OrderRequestDto;
import com.technicaltest.sb.orders.model.dto.OrderResponseDto;
import com.technicaltest.sb.orders.model.dto.ProductDto;
import com.technicaltest.sb.orders.model.enums.OrderStatus;
import com.technicaltest.sb.orders.repository.OrderRepository;
import com.technicaltest.sb.orders.service.interfaces.IOrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements IOrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	

	@Autowired
	private IProductsClientService productsClientService;
	
	
	@Override
	public OrderResponseDto addOrder(OrderRequestDto orderRequest) {
		validateProductsFromOrder(orderRequest);
		try {
			Order order = Order.builder()
					.user(orderRequest.getUser())
					.orderCreatedDate(LocalDate.now())
					.orderStatus(OrderStatus.PENDING).build();
			
			this.orderRepository.saveAndFlush(order);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return null;
	}
	
	private void validateProductsFromOrder(OrderRequestDto requestDto) {
		if(requestDto.getProducts() == null && requestDto.getProducts().isEmpty()) {
			throw new BadRequestException("The purchase order must have at least one product to be processed.");
		}
		
		for (ProductDto product : requestDto.getProducts()) {
			ProductDto tempProduct = productsClientService.getById(product.getId().intValue());
			if(tempProduct == null) { throw new AppServiceException("The product with id: " + product.getId() + " is not found."); } 
			if(product.getQuantity() > tempProduct.getQuantity()) {
				throw new BadRequestException("The quantity for the product: " + tempProduct.getTitle() + ", is exceeded. Try with a lower number!");
			}
		}
	}

}
