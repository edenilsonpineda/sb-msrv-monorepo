package com.technicaltest.sb.orders.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto implements Serializable{
	
	private static final long serialVersionUID = -2705257951107427824L;
	
	private BigDecimal total;
	private LocalDate paymentDate;
	
}
