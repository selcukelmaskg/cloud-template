package com.poc.converterservice.controller;

import com.crmpoc.customer.CustomerDetails;
import com.poc.converterservice.service.customer.CustomerService;
import org.junit.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(CustomerController.END_POINT)
public class CustomerController {
    public static final String END_POINT = "/api/customer";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDetails> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @PostMapping("/{tckn}")
    public CustomerDetails getCustomerDetails(@PathVariable(name = "tckn") Long tckn) {
        Assert.assertTrue("Tckn must be not empty", tckn > 0);
        return customerService.getCustomerDetails(tckn);
    }

    @PostMapping
    public void createCustomerRequest(@RequestBody CustomerDetails customerDetails) {
        customerService.createCustomer(customerDetails);
    }

    @PutMapping
    public void updateCustomerRequest(@RequestBody CustomerDetails customerDetails) {
        customerService.updateCustomer(customerDetails);
    }

    @GetMapping("/validation/{tckn}")
    public Boolean customerValidation(@PathVariable(name = "tckn") Long tckn) {
        Assert.assertTrue("Tckn must be not empty", tckn > 0);
        return customerService.customerValidation(tckn);
    }
}
