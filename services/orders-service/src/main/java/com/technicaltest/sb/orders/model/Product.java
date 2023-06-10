package com.technicaltest.sb.orders.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.technicaltest.sb.orders.model.base.BaseEntity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
public class Product extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@NotNull
	private String title;
	
	@NotNull
	private BigDecimal price;
	
	@NotNull
	private String description;
	

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
}
