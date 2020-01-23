package com.poc.converterservice.service.catalog;

import com.crmpoc.catalog.ProductDetails;

import java.util.List;

public interface CatalogService {
    void createProduct(ProductDetails productDetails);

    List<ProductDetails> getAllProducts();

    ProductDetails getProductDetails(Long id);

    void updateProduct(ProductDetails productDetails);

    Boolean orderFulfilment(Long id);
}
