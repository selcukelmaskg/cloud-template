package com.cloudtemplate.customerservice.controller;

import com.cloudtemplate.customerservice.domain.Customer;
import com.cloudtemplate.customerservice.service.customer.CustomerService;
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
    static final String END_POINT = "/api/customer";
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{tckn}")
    public Customer getCustomerDetailById(@PathVariable(name = "tckn") Long tckn) {
        return customerService.findById(tckn);
    }

    // TODO: bilalkocoglu => replace this persistent entity with dto object
    @PostMapping
    public void createCustomer(@RequestBody Customer customer) {
        customerService.save(customer);
    }

    @GetMapping
    public List<Customer> allCustomers() {
        return customerService.findAll();
    }

    // TODO: bilalkocoglu => replace this persistent entity with dto object
    @PutMapping
    public void updateCustomer(@RequestBody Customer customer) {
        customerService.update(customer);
    }
}
