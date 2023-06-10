package com.technicaltest.sb.orders.service.interfaces;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "products-client", url = "${app.constants.api.consumer.local-products-service}")
public interface IProductsLocalFeignClientService {

}
