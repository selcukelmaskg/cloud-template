package com.cloudtemplate.orderservice.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("converter-service")
public interface ConverterClient {

    @GetMapping("api/customer/validation/{tckn}")
    Boolean customerValidation(@PathVariable(name = "tckn") Long tckn);

    @GetMapping("api/catalog//order-fulfilment/{id}")
    Boolean orderFulfilment(@PathVariable(name = "id") Long id);
}
