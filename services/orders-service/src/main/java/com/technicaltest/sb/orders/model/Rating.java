package com.technicaltest.sb.orders.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rating implements Serializable {
	
	private static final long serialVersionUID = -6236358438012566962L;
	
	private Double rate;
	private Integer count;

}
