package com.technicaltest.sb.products.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technicaltest.sb.products.model.dto.ProductDto;
import com.technicaltest.sb.products.service.interfaces.IProductFakeApiFeignClientService;
import com.technicaltest.sb.products.utils.mapper.ProductsHateoasMapper;
import com.technicaltest.sb.products.utils.web.AppServiceException;
import com.technicaltest.sb.products.utils.web.ResponseEntityUtil;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @author edenilson
 * @version 1.0
 * @since 1.0
 */

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	private ProductsHateoasMapper productsHateoasMapper;
	
	@Autowired
	private IProductFakeApiFeignClientService productService;
	
	@Autowired
	private ResponseEntityUtil responseEntityUtil;
	
	@GetMapping
	public ResponseEntity<?> getAllProducts(@RequestHeader HttpHeaders httpHeaders,
			@RequestParam(required = false) Integer limit){
		ResponseEntity<?> responseEntity = null;
		try {
			List<ProductDto> products;
			
			if(limit != null && limit > 0) {
				products = productService.getAllByLimit(limit.toString());
			}else {
				products = productService.getAll();
			}
			
			CollectionModel<ProductDto> result = productsHateoasMapper.mapProductsToModel(products);
			
			responseEntity = responseEntityUtil.createOkResponse(result, HttpStatus.OK.toString(), "");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new AppServiceException("An error ocurred, products cannot be retrieved.");
		}
		return responseEntity;
	}
}
