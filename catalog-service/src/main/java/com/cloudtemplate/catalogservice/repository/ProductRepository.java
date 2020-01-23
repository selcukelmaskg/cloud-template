package com.cloudtemplate.catalogservice.repository;

import com.cloudtemplate.catalogservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
