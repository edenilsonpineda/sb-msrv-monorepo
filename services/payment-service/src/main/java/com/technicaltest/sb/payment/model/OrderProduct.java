package com.technicaltest.sb.payment.model;

import java.math.BigDecimal;

import com.technicaltest.sb.payment.model.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrderProduct extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	
	@Column(nullable = false)
	private Integer quantity;

	@Column(nullable = false)
	private BigDecimal total;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "order_id", nullable = false, updatable = false)
	private Order order;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	@Transient
	public Double getTotalPrice() {
		return getProduct().getPrice().doubleValue() * getQuantity();
	}
}
