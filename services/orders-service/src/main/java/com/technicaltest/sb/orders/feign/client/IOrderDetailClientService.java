package com.technicaltest.sb.orders.feign.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "order-details-client", url = "${app.constants.api.consumer.local-orderdetails-service}")
public interface IOrderDetailClientService {

}
