package com.technicaltest.sb.orders.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.technicaltest.sb.orders.model.base.BaseEntity;
import com.technicaltest.sb.orders.model.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "orders")
public class Order extends BaseEntity {
	
	private static final long serialVersionUID = -2271837054938297195L;
	
	@Builder.Default
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus = OrderStatus.PENDING; // by default all orders will be in a pending state
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderProduct> productsOrders;
	
	@Column(nullable = false)
	private BigDecimal total;
	
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column
	private LocalDate orderCreatedDate;
	
	@Transient
	BigDecimal getTotalOrder() {
		BigDecimal sum = new BigDecimal(0);
		List<OrderProduct> productOrders = getProductsOrders();
		for (OrderProduct productOrder : productOrders) {
			sum = sum.add(productOrder.getTotal());
		}
		
		return sum;
	}
	
	@Transient
	public int getTotalProducts() {
		return this.getProductsOrders().size();
	}
	
	@ManyToOne(optional = false)
	private User user;
}
