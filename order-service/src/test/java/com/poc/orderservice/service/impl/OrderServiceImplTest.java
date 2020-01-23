package com.poc.orderservice.service.impl;

import com.poc.orderservice.client.feign.AssetClient;
import com.poc.orderservice.client.feign.ConverterClient;
import com.poc.orderservice.domain.Order;
import com.poc.orderservice.repository.OrderRepository;
import com.poc.shared.enumeration.OrderStatus;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private OrderRepository repository;
    private AssetClient assetClient;
    private ConverterClient converterClient;
    private Environment environment;
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        repository = mock(OrderRepository.class);
        assetClient = mock(AssetClient.class);
        converterClient = mock(ConverterClient.class);
        environment = mock(Environment.class);
        orderService = new OrderServiceImpl(repository, assetClient, environment, converterClient);
    }

    @Test
    public void existOrder() {
        Order order = getMockOrderData();

        when(repository.existsById(order.getId())).thenReturn(true);

        Assert.assertEquals(true, orderService.existOrder(order.getId()));
        Assert.assertEquals(false, orderService.existOrder("x"));
    }

    @Test
    public void findAll() {
        int page = 0;
        int size = 10;
        List<Order> allOrders = getMockOrderList();

        when(repository.findAll(PageRequest.of(page, size))).thenReturn(new PageImpl<>(allOrders));
        when(repository.count()).thenReturn(Long.valueOf(allOrders.size()));

        Assert.assertEquals(new PageImpl<>(allOrders, PageRequest.of(page, size), allOrders.size()), orderService.findAll(page, size));
    }

    @Test
    public void orderByTckn() {
        List<Order> allOrders = getMockOrderList();

        when(repository.findByTckn(1L)).thenReturn(allOrders);
        when(repository.findByTckn(2L)).thenReturn(Collections.emptyList());

        Assert.assertEquals(allOrders, orderService.orderByTckn(1L));
        Assert.assertEquals(Collections.emptyList(), orderService.orderByTckn(2L));
    }

    private Order getMockOrderData() {
        Order order = new Order();
        order.setId("ASDF1234ASCA1231ASDF");
        order.setProductId(34L);
        order.setTckn(Long.valueOf("123412345932"));
        order.setStatus(OrderStatus.PENDING);

        return order;
    }

    private List<Order> getMockOrderList() {
        Order order = getMockOrderData();

        List<Order> allOrders = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            allOrders.add(order);
        }

        return allOrders;
    }
}