package com.technicaltest.sb.orders.model.helper;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.technicaltest.sb.orders.exception.AppServiceException;
import com.technicaltest.sb.orders.exception.BadRequestException;
import com.technicaltest.sb.orders.feign.client.IProductsClientService;
import com.technicaltest.sb.orders.model.dto.OrderRequestDto;
import com.technicaltest.sb.orders.model.dto.ProductDto;

/**
 * 
 * @author edenilson
 *
 */
public class OrderHelper {
	
	@Autowired
	IProductsClientService productsClientService;

	
	public void validateProductFromOrder(OrderRequestDto requestDto) {
		if(requestDto.getProducts().isEmpty()) {
			throw new BadRequestException("The order must have one or more items to be completed.");
		}
		
		for (ProductDto product : requestDto.getProducts()) {
			ProductDto tempProduct = productsClientService.getById(product.getId().intValue());
			if(tempProduct == null) { throw new AppServiceException("The product with id: " + product.getId() + " is not found."); } 
			if(product.getQuantity() > tempProduct.getQuantity()) {
				throw new BadRequestException("The quantity for the product: " + tempProduct.getTitle() + ", is exceeded. Try with a lower number!");
			}
		}
	}
	
	public BigDecimal getOrderTotal(OrderRequestDto requestDto) {
		BigDecimal total = BigDecimal.ZERO;
		
		
		for (ProductDto product : requestDto.getProducts()) {
			total = total.add(product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())));
		}
		
		return total;
	}
}
