package com.poc.assetservice.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("order-service")
public interface OrderClient {
    @GetMapping("api/order/validate/{orderId}")
    Boolean validateOrder(@PathVariable(value = "orderId") String orderId);
}