package com.technicaltest.sb.products.model.dto;

import java.io.Serializable;
<<<<<<< HEAD
import java.math.BigDecimal;
=======
>>>>>>> main

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "products")
@JsonInclude(Include.NON_NULL)
public class ProductDto extends RepresentationModel<ProductDto> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String title;
<<<<<<< HEAD
	private BigDecimal price;
=======
	private Long price;
>>>>>>> main
	private String description;
	
	private Rating rating;

}
