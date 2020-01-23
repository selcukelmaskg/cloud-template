package com.poc.orderservice.repository;

import com.poc.orderservice.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByTckn(Long tckn);
}
