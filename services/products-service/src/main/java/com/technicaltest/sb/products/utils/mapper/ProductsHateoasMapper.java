package com.technicaltest.sb.products.utils.mapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.technicaltest.sb.products.controller.ProductsController;
import com.technicaltest.sb.products.model.dto.ProductDto;


@Component
public class ProductsHateoasMapper {
	
	
	public CollectionModel<ProductDto> mapProductsToModel(List<ProductDto> products){
		if(products == null) throw new NullPointerException("Products list is not defined");
		if(!products.isEmpty()) {
			products.forEach(p -> {
				long productId = p.getId();
				Link selfLink = linkTo(ProductsController.class)
						.slash(productId)
						.withSelfRel();
				p.add(selfLink);
			});
		}
		
		Link selfReLink = linkTo(ProductsController.class).withSelfRel();
		return CollectionModel.of(products, selfReLink);
	}
	
	public EntityModel<ProductDto> mapProductAsEntityModel(ProductDto product){
		if(product == null) throw new NullPointerException("Product is null");
		
		EntityModel<ProductDto> productEntityModel = EntityModel.of(product);
		productEntityModel.add(linkTo(ProductsController.class).slash(product.getId()).withSelfRel())
			.add(linkTo(ProductsController.class).withRel("products").withType(HttpMethod.GET.name()));
		
		return productEntityModel;
	}
}
