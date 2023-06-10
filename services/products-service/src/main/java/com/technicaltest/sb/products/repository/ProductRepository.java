package com.technicaltest.sb.products.repository;

import org.springframework.data.repository.CrudRepository;

import com.technicaltest.sb.products.model.dto.ProductDto;

/**
 * Spring data {@link CrudRepository} to store {@link Product}s
 * 
 * @author edenilson
 *
 */
public interface ProductRepository extends CrudRepository<ProductDto, Long> {}
