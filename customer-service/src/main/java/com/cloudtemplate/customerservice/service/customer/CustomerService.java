package com.cloudtemplate.customerservice.service.customer;

import com.cloudtemplate.customerservice.domain.Customer;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface CustomerService {
    Customer findById(Long tckn);

    List<Customer> findAll();

    @Async
    void save(Customer customer);

    @Async
    void update(Customer customer);

    Boolean existInCustomers(Long tckn);
}
