package com.poc.converterservice.service.customer.impl;

import com.crmpoc.customer.CreateCustomerRequest;
import com.crmpoc.customer.CustomerDetails;
import com.crmpoc.customer.CustomerPort;
import com.crmpoc.customer.CustomerValidationRequest;
import com.crmpoc.customer.GetAllCustomerRequest;
import com.crmpoc.customer.GetCustomerDetailsRequest;
import com.crmpoc.customer.UpdateCustomerRequest;
import com.poc.converterservice.service.customer.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerPort customerClient;

    public CustomerServiceImpl(CustomerPort customerClient) {
        this.customerClient = customerClient;
    }

    @Override
    public List<CustomerDetails> getAllCustomer() {
        return customerClient.getAllCustomer(new GetAllCustomerRequest()).getAddresses();
    }

    @Override
    public void createCustomer(CustomerDetails customerDetails) {
        CreateCustomerRequest request = new CreateCustomerRequest();
        request.setCustomerDetails(customerDetails);
        customerClient.createCustomer(request);
    }

    @Override
    public void updateCustomer(CustomerDetails customerDetails) {
        UpdateCustomerRequest request = new UpdateCustomerRequest();
        request.setCustomer(customerDetails);
        customerClient.updateCustomer(request);
    }

    @Override
    public CustomerDetails getCustomerDetails(Long tckn) {
        GetCustomerDetailsRequest request = new GetCustomerDetailsRequest();
        request.setTckn(tckn);
        return customerClient.getCustomerDetails(request).getCustomerDetails();
    }

    public Boolean customerValidation(Long tckn) {
        CustomerValidationRequest request = new CustomerValidationRequest();
        request.setTckn(tckn);
        return customerClient.customerValidation(request).isValidate();
    }
}
