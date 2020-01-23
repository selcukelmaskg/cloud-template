package com.cloudtemplate.productservice.repository;

import com.cloudtemplate.productservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
