package com.cloudtemplate.productservice.service.impl;

import com.cloudtemplate.productservice.domain.Product;
import com.cloudtemplate.productservice.repository.ProductRepository;
import com.cloudtemplate.productservice.service.ProductService;
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
    public Product findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Cacheable
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    @CacheEvict(allEntries = true)
    public void save(Product product) {
        Product savedProduct = repository.save(product);
        log.info("[productSaved]: Clear product cache !");
        log.info("[productSaved]: Data:\n {}", savedProduct);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void update(Product product) {
        if (!repository.existsById(product.getId())) {
            log.warn("id: {} product not found.", product.getId());
            return;
        }

        save(product);
    }

    @Override
    @CacheEvict(allEntries = true)
    public Boolean orderRegistration(Long productId) {
        Product product = repository.findById(productId).orElse(null);

        if (product == null || product.getStock() < 1 || !product.isEnable()) {
            return false;
        } else {
            product.setStock(product.getStock() - 1);
            repository.save(product);
            return true;
        }
    }
}
