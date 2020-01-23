package com.poc.converterservice.service.customer;

import com.crmpoc.customer.CustomerDetails;

import java.util.List;

public interface CustomerService {
    List<CustomerDetails> getAllCustomer();

    void createCustomer(CustomerDetails customerDetails);

    void updateCustomer(CustomerDetails customerDetails);

    CustomerDetails getCustomerDetails(Long tckn);

    Boolean customerValidation(Long tckn);
}
