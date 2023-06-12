package com.technicaltest.sb.orders.feign.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.technicaltest.sb.orders.feign.client.IProductsClientService;
import com.technicaltest.sb.orders.model.dto.ProductDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProductsServiceClientFallbackFactory implements FallbackFactory<IProductsClientService> {

	@Override
	public IProductsClientService create(Throwable cause) {
		log.error("An error has ocurred while trying to connect to the Products API client", cause);
		
		return new IProductsClientService() {
			
			@Override
			public ProductDto getById(Integer id) {
				
				return ProductDto.builder().id(0l).title("").description("").price(BigDecimal.ZERO).build();
			}
			
			@Override
			public List<ProductDto> getAllByLimit(String size) {
				
				return new ArrayList<>();
			}
			
			@Override
			public List<ProductDto> getAll() {
				
				return new ArrayList<>();
			}
		};
	}

}
