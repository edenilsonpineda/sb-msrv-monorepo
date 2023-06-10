package com.technicaltest.sb.orders.model.dto;

import java.util.List;

import com.technicaltest.sb.orders.model.Product;
import com.technicaltest.sb.orders.model.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
	private Long id;
	private OrderStatus orderStatus;
	private List<Product> products;
	private Long customerId;
	
}
