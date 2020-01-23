package com.cloudtemplate.catalogservice.service.impl;

import com.cloudtemplate.catalogservice.domain.Product;
import com.cloudtemplate.catalogservice.repository.ProductRepository;
import com.crmpoc.catalog.ProductDetails;
import com.cloudtemplate.catalogservice.service.ProductService;
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
        ProductDetails productDetails = getMockProductDetail();

        when(productRepository.findById(1L)).thenReturn(Optional.of(ObjectMapperUtils.map(productDetails, Product.class)));
        when(productRepository.findById(2L)).thenReturn(Optional.empty());

        Assert.assertEquals(productDetails.getId(), productService.findById(1L).getId());
        Assert.assertNull(productService.findById(2L));
        log.info("[productService -> findById]: successfully completed !");
    }

    @Test
    public void findAll() {
        ProductDetails productDetails = getMockProductDetail();
        List<ProductDetails> allProductDetails = new ArrayList<>();

        for(int i = 0; i < 5; i++) {
            allProductDetails.add(productDetails);
        }

        List<Product> allProducts = ObjectMapperUtils.mapAll(allProductDetails, Product.class);

        when(productRepository.findAll()).thenReturn(allProducts);

        Assert.assertEquals(allProductDetails.size(), productService.findAll().size());
        log.info("[productService -> findAll]: successfully completed !");
    }

    @Test
    public void orderRegistration() {
        ProductDetails productDetails = getMockProductDetail();
        ProductDetails outOfStockProductDetaild = getOutOfStockMockProductDetail();

        when(productRepository.findById(2L)).thenReturn(Optional.of(ObjectMapperUtils.map(outOfStockProductDetaild, Product.class)));
        when(productRepository.findById(3L)).thenReturn(Optional.empty());

        when(productRepository.save(any(Product.class))).thenReturn(ObjectMapperUtils.map(productDetails, Product.class));

        Assert.assertEquals(false, productService.orderRegistration(2L));
        Assert.assertEquals(false, productService.orderRegistration(3L));

        log.info("[productService -> orderRegistration]: successfully completed !");
    }

    private ProductDetails getMockProductDetail() {
        ProductDetails mockProductDetail = new ProductDetails();
        mockProductDetail.setId(1);
        mockProductDetail.setName("test");
        mockProductDetail.setPrice(100);
        mockProductDetail.setStock(150);
        mockProductDetail.setCurrency("testl");

        return mockProductDetail;
    }

    private ProductDetails getOutOfStockMockProductDetail() {
        ProductDetails mockProductDetail = new ProductDetails();
        mockProductDetail.setId(1);
        mockProductDetail.setName("test");
        mockProductDetail.setPrice(100);
        mockProductDetail.setStock(0);
        mockProductDetail.setCurrency("testl");

        return mockProductDetail;
    }

}