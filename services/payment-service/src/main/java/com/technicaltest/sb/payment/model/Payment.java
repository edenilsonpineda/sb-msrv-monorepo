package com.technicaltest.sb.payment.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.technicaltest.sb.payment.model.base.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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
public class Payment extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	private BigDecimal amount;
	private LocalDate paymentDate;
	
	@OneToOne(mappedBy = "payment_id", cascade = CascadeType.ALL, optional = false)
	private Order order;
}
