package com.cloudtemplate.productservice.controller;

import com.cloudtemplate.productservice.domain.Product;
import com.cloudtemplate.productservice.service.ProductService;
import com.cloudtemplate.shared.dto.product.OrderFulfilmentRequest;
import org.junit.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ProductController.END_POINT)
public class ProductController {
    static final String END_POINT = "/api/product";
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductDetailById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @PostMapping
    public void saveProduct(@RequestBody Product product) {
        productService.save(product);
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product) {
        productService.update(product);
    }

    @PostMapping("/order-fulfilment")
    public Boolean orderFulfilment(@RequestBody OrderFulfilmentRequest orderFulfilmentRequest) {
        Assert.assertTrue(orderFulfilmentRequest.getProductId() > 0);
        return productService.orderRegistration(orderFulfilmentRequest.getProductId());
    }
}
