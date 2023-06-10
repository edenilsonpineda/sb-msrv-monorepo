package com.technicaltest.sb.products.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.technicaltest.sb.products.model.dto.ProductDto;
import com.technicaltest.sb.products.model.dto.Rating;
import com.technicaltest.sb.products.service.interfaces.IProductFakeApiFeignClientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProductsServiceClientFallbackFactory implements FallbackFactory<IProductFakeApiFeignClientService> {

	@Override
	public IProductFakeApiFeignClientService create(Throwable cause) {
		log.error("An error has ocurred while trying to connect to the Products API client", cause);
		
		return new IProductFakeApiFeignClientService() {
			
			@Override
			public ProductDto getById(Integer id) {
			
				return new ProductDto(0l, "", BigDecimal.ZERO, "", Rating.builder().rate(0.00).count(0).build());
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
