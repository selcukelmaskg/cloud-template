package com.cloudtemplate.orderservice.repository;

import com.cloudtemplate.orderservice.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByTckn(Long tckn);
}
