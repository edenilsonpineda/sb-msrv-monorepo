package com.technicaltest.sb.products.service.interfaces;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.technicaltest.sb.products.config.ProductsServiceClientFallbackFactory;
import com.technicaltest.sb.products.model.dto.ProductDto;


@FeignClient(name = "products-client", url = "${app.constants.api.consumer.fake-api-url}", fallbackFactory = ProductsServiceClientFallbackFactory.class)
public interface IProductFakeApiFeignClientService {
	@GetMapping("/products")
	List<ProductDto> getAll();
	
	@GetMapping("/products?limit={size}")
	List<ProductDto> getAllByLimit(@RequestParam(value = "size", name = "size") String size);
	
	@GetMapping("/products/{id}")
	ProductDto getById(@RequestParam(value = "productId", name = "productId") Integer id);
}
