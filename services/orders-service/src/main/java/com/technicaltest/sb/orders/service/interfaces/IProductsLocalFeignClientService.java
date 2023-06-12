package com.technicaltest.sb.orders.service.interfaces;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.technicaltest.sb.orders.model.dto.ProductDto;

@FeignClient(name = "products-client", url = "${app.constants.api.consumer.local-products-service}")
public interface IProductsLocalFeignClientService {
	@GetMapping("/products")
	List<ProductDto> getAll();
	
	@GetMapping("/products?limit={size}")
	List<ProductDto> getAllByLimit(@RequestParam(value = "size", name = "size") String size);
	
	@GetMapping("/products/{id}")
	ProductDto getById(@RequestParam(value = "productId", name = "productId") Integer id);
}
