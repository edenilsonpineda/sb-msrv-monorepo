package com.technicaltest.sb.products.utils.mapper;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.technicaltest.sb.products.controller.ProductsController;
import com.technicaltest.sb.products.model.dto.ProductDto;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


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

}
