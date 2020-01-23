package com.cloudtemplate.orderservice.service;

import com.cloudtemplate.orderservice.domain.Order;
import com.cloudtemplate.orderservice.dto.CreateOrderRequest;
import com.cloudtemplate.orderservice.dto.CreateOrderResponse;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public interface OrderService {
    CreateOrderResponse save(CreateOrderRequest createOrderRequest) throws InterruptedException;

    PageImpl<Order> findAll(int page, int size);

    Boolean existOrder(String orderId);

    List<Order> orderByTckn(Long tckn);
}
