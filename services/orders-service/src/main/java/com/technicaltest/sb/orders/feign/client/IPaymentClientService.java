package com.technicaltest.sb.orders.feign.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "payments-client", url = "${app.constants.api.consumer.local-payment-service}")
public interface IPaymentClientService {

}
