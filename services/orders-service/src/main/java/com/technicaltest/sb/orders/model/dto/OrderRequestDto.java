package com.technicaltest.sb.orders.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.technicaltest.sb.orders.model.User;
import com.technicaltest.sb.orders.model.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private OrderStatus orderStatus;
	
	private List<ProductDto> products = new ArrayList<>();
	
	
	private transient User user;
}
