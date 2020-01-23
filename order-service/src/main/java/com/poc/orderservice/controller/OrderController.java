package com.poc.orderservice.controller;

import com.poc.orderservice.dto.CreateOrderRequest;
import com.poc.orderservice.domain.Order;
import com.poc.orderservice.dto.CreateOrderResponse;
import com.poc.orderservice.service.OrderService;
import org.junit.Assert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(OrderController.END_POINT)
public class OrderController {
    static final String END_POINT = "/api/order";

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public CreateOrderResponse createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest) throws InterruptedException {
        return this.orderService.save(createOrderRequest);
    }

    @GetMapping
    public PageImpl<Order> allOrder(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                    @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return this.orderService.findAll(page, size);
    }

    @GetMapping("/validate/{orderId}")
    public Boolean validateOrder(@PathVariable(value = "orderId") String orderId) {
        return this.orderService.existOrder(orderId);
    }

    @GetMapping("/{tckn}")
    public List<Order> orderByTckn(@PathVariable(value = "tckn") Long tckn) {
        Assert.assertTrue("Tckn must be not empty", tckn > 0);
        return this.orderService.orderByTckn(tckn);
    }
}
