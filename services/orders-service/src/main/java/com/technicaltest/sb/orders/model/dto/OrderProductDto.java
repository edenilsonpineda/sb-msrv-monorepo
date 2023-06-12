package com.technicaltest.sb.orders.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDto {
	
	private Long productId;
	private Long orderId;
	private Integer quantity;
	private BigDecimal totalPrice;
	
	

}
