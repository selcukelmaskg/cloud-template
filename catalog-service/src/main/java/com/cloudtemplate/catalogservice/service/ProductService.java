package com.cloudtemplate.catalogservice.service;

import com.crmpoc.catalog.ProductDetails;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface ProductService {
    ProductDetails findById(Long id);

    List<ProductDetails> findAll();

    @Async
    void save(ProductDetails productDetails);

    @Async
    void update(ProductDetails productDetails);

    Boolean orderRegistration(Long productId);
}
