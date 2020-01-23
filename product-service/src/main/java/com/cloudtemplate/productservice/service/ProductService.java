package com.cloudtemplate.productservice.service;

import com.cloudtemplate.productservice.domain.Product;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface ProductService {
    Product findById(Long id);

    List<Product> findAll();

    @Async
    void save(Product productDetails);

    @Async
    void update(Product productDetails);

    Boolean orderRegistration(Long productId);
}
