package com.poc.customerservice.service.customer;

import com.crmpoc.customer.CustomerDetails;
import javassist.NotFoundException;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface CustomerService {
    CustomerDetails findById(Long tckn);

    List<CustomerDetails> findAll();

    @Async
    void save(CustomerDetails customerDetails);

    @Async
    void update(CustomerDetails customerDetails);

    Boolean existInCustomers(Long tckn);
}
