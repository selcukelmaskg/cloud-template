package com.poc.orderservice.client.feign;

import com.poc.shared.dto.asset.AssetBindingRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient("asset-service")
public interface AssetClient {
    @GetMapping(value = "api/asset/binding")
    String assetBinding(@RequestBody @Valid AssetBindingRequest assetBindingRequest);
}
