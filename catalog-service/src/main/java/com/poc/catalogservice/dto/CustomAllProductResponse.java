package com.poc.catalogservice.dto;

import com.crmpoc.catalog.GetAllProductResponse;
import com.crmpoc.catalog.ProductDetails;

import java.util.List;

public class CustomAllProductResponse extends GetAllProductResponse {
    public void setProducts(List<ProductDetails> productDetails) {
        this.productDetails = productDetails;
    }
}
