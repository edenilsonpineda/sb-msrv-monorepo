package com.technicaltest.sb.orders.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductDto extends RepresentationModel<ProductDto> implements Serializable{
	
	private static final long serialVersionUID = -6068295815022200185L;
	
	private Long id;
	private String title;
	private BigDecimal price;
	private String description;
	private Integer quantity;
}
