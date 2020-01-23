package com.cloudtemplate.productservice.service.impl;

import com.cloudtemplate.productservice.domain.Product;
import com.cloudtemplate.productservice.repository.ProductRepository;
import com.cloudtemplate.productservice.service.ProductService;
import com.cloudtemplate.shared.util.ObjectMapperUtils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void findById() {
        Product product = getMockProduct();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.findById(2L)).thenReturn(Optional.empty());

        Assert.assertEquals(product, productService.findById(1L));
        Assert.assertNull(productService.findById(2L));
        log.info("[productService -> findById]: successfully completed !");
    }

    @Test
    public void findAll() {
        List<Product> allProducts = getProducts();

        when(productRepository.findAll()).thenReturn(allProducts);

        Assert.assertEquals(allProducts, productService.findAll());
        log.info("[productService -> findAll]: successfully completed !");
    }

    @Test
    public void orderRegistration() {
        Product product = getMockProduct();
        Product outOfStockProduct = getOutOfStockMockProduct();

        when(productRepository.findById(2L)).thenReturn(Optional.of(outOfStockProduct));
        when(productRepository.findById(3L)).thenReturn(Optional.empty());

        when(productRepository.save(any(Product.class))).thenReturn(ObjectMapperUtils.map(product, Product.class));

        Assert.assertEquals(false, productService.orderRegistration(2L));
        Assert.assertEquals(false, productService.orderRegistration(3L));

        log.info("[productService -> orderRegistration]: successfully completed !");
    }

    private Product getMockProduct() {
        Product mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setName("test");
        mockProduct.setPrice(100);
        mockProduct.setStock(150L);
        mockProduct.setCurrency("testl");

        return mockProduct;
    }

    private Product getOutOfStockMockProduct() {
        Product mockProductDetail = new Product();
        mockProductDetail.setId(1L);
        mockProductDetail.setName("test");
        mockProductDetail.setPrice(100);
        mockProductDetail.setStock(0L);
        mockProductDetail.setCurrency("testl");

        return mockProductDetail;
    }

    private List<Product> getProducts() {
        Product product = getMockProduct();
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            products.add(product);
        }

        return products;
    }
}