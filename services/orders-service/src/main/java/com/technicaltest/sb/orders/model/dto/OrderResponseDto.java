package com.technicaltest.sb.orders.model.dto;

import java.math.BigDecimal;
import java.util.List;

import com.technicaltest.sb.orders.model.Product;
import com.technicaltest.sb.orders.model.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
	private Long id;
	private OrderStatus orderStatus;
	private BigDecimal total;
	private List<Product> products;
}
