package com.cloudtemplate.customerservice.repository;

import com.cloudtemplate.customerservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
