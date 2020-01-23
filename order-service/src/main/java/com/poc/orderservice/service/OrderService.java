package com.poc.orderservice.service;

import com.poc.orderservice.dto.CreateOrderRequest;
import com.poc.orderservice.domain.Order;
import com.poc.orderservice.dto.CreateOrderResponse;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    CreateOrderResponse save(CreateOrderRequest createOrderRequest) throws InterruptedException;

    PageImpl<Order> findAll(int page, int size);

    Boolean existOrder(String orderId);

    List<Order> orderByTckn(Long tckn);
}
