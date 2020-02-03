package com.cloudtemplate.assetservice.accessor;

import com.cloudtemplate.shared.client.OrderClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("order-service")
public interface OrderServiceAccessor extends OrderClient {
}
