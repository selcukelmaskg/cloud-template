package com.poc.converterservice.controller;

import com.crmpoc.catalog.ProductDetails;
import com.poc.converterservice.service.catalog.CatalogService;
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
@RequestMapping(CatalogController.END_POINT)
public class CatalogController {
    public static final String END_POINT = "/api/catalog";

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public List<ProductDetails> findAll() {
        return catalogService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDetails findById(@PathVariable(name = "id") Long id) {
        Assert.assertTrue("Id must be not empty",id > 0);
        return catalogService.getProductDetails(id);
    }

    @PostMapping()
    public void createProduct(@RequestBody ProductDetails productDetails) {
        catalogService.createProduct(productDetails);
    }

    @PutMapping()
    public void updateProduct(@RequestBody ProductDetails productDetails) {
        catalogService.updateProduct(productDetails);
    }

    @GetMapping("/order-fulfilment/{id}")
    public Boolean orderFulfilment(@PathVariable(name = "id") Long id) {
        Assert.assertTrue("Id must be not empty", id > 0);
        return catalogService.orderFulfilment(id);
    }
}
