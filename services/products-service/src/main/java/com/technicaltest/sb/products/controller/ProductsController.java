package com.technicaltest.sb.products.controller;

<<<<<<< HEAD
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
=======
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
>>>>>>> main
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technicaltest.sb.products.model.dto.ProductDto;
<<<<<<< HEAD
import com.technicaltest.sb.products.service.interfaces.IProductFakeApiFeignClientService;
import com.technicaltest.sb.products.utils.mapper.ProductsHateoasMapper;
=======
import com.technicaltest.sb.products.service.interfaces.IProductService;
>>>>>>> main
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
<<<<<<< HEAD
	private ProductsHateoasMapper productsHateoasMapper;
	
	@Autowired
	private IProductFakeApiFeignClientService productService;
=======
	private IProductService productService;
>>>>>>> main
	
	@Autowired
	private ResponseEntityUtil responseEntityUtil;
	
	@GetMapping
	public ResponseEntity<?> getAllProducts(@RequestHeader HttpHeaders httpHeaders,
			@RequestParam(required = false) Integer limit){
		ResponseEntity<?> responseEntity = null;
		try {
			List<ProductDto> products;
			
<<<<<<< HEAD
			if(limit != null && limit > 0) {
=======
			if(limit > 0) {
>>>>>>> main
				products = productService.getAllByLimit(limit.toString());
			}else {
				products = productService.getAll();
			}
			
<<<<<<< HEAD
			CollectionModel<ProductDto> result = productsHateoasMapper.mapProductsToModel(products);
=======
			for (final ProductDto productDto : products) {
				long productId = productDto.getId();
				Link selfLink = linkTo(ProductsController.class).slash(productId)
						.withSelfRel();
				productDto.add(selfLink);
				
			}
			
			Link link = linkTo(ProductsController.class).withSelfRel();
			CollectionModel<ProductDto> result = CollectionModel.of(products, link);
>>>>>>> main
			
			responseEntity = responseEntityUtil.createOkResponse(result, HttpStatus.OK.toString(), "");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new AppServiceException("An error ocurred, products cannot be retrieved.");
		}
		return responseEntity;
	}
}
