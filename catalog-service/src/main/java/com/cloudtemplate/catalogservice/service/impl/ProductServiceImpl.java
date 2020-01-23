package com.cloudtemplate.catalogservice.service.impl;

import com.cloudtemplate.catalogservice.domain.Product;
import com.cloudtemplate.catalogservice.repository.ProductRepository;
import com.cloudtemplate.catalogservice.service.ProductService;
import com.crmpoc.catalog.ProductDetails;
import com.cloudtemplate.shared.util.ObjectMapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "product")
public class ProductServiceImpl implements ProductService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable(key = "#id")
    public ProductDetails findById(Long id) {
        return ObjectMapperUtils.map(repository.findById(id).orElse(null), ProductDetails.class);
    }

    @Override
    @Cacheable
    public List<ProductDetails> findAll() {
        return ObjectMapperUtils.mapAll(repository.findAll(), ProductDetails.class);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void save(ProductDetails productDetails) {
        Product product = repository.save(ObjectMapperUtils.map(productDetails, Product.class));
        log.info("[productSaved]: Clear product cache !");
        log.info("[productSaved]: Data:\n {}", product);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void update(ProductDetails productDetails) {
        if (!repository.existsById(productDetails.getId())) {
            log.warn("id: {} product not found.", productDetails.getId());
            return;
        }

        save(productDetails);
    }

    @Override
    @CacheEvict(allEntries = true)
    public Boolean orderRegistration(Long productId) {
        ProductDetails productDetails = ObjectMapperUtils.map(repository.findById(productId).orElse(null), ProductDetails.class);

        if (productDetails == null || productDetails.getStock() < 1 || !productDetails.isEnable()) {
            return false;
        } else {
            productDetails.setStock(productDetails.getStock() - 1);
            repository.save(ObjectMapperUtils.map(productDetails, Product.class));
            return true;
        }
    }
}
