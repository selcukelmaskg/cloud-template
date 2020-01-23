package com.cloudtemplate.customerservice.service.customer.impl;


import com.cloudtemplate.customerservice.domain.Address;
import com.cloudtemplate.customerservice.domain.Customer;
import com.cloudtemplate.customerservice.repository.CustomerRepository;
import com.cloudtemplate.customerservice.service.customer.CustomerService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    public void findById() {
        Customer customer = getMockCustomer();

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.findById(2L)).thenReturn(Optional.empty());

        Assert.assertEquals(customer.getTckn(), customerService.findById(1L).getTckn());
        Assert.assertNull(customerService.findById(2L));
        log.info("[customerService -> findById]: successfully completed !");
    }

    @Test
    public void findAll() {
        List<Customer> customers = getMockCustomers();

        when(customerRepository.findAll()).thenReturn(customers);

        Assert.assertEquals(customers, customerService.findAll());
        log.info("[customerService -> findAll]: successfully completed !");
    }

    private Customer getMockCustomer() {
        Customer mockProductDetail = new Customer();
        mockProductDetail.setTckn(Long.parseLong("67204082123"));
        mockProductDetail.setName("lol");
        mockProductDetail.setSurname("lul");
        mockProductDetail.setBirthYear(1990);

        Address addressDetail = new Address();
        addressDetail.setId(Long.parseLong("1"));
        addressDetail.setCity("Istanbul");
        addressDetail.setCountry("Türkiye");
        addressDetail.setStreet("Bağdat");

        mockProductDetail.setAddress(addressDetail);

        return mockProductDetail;
    }

    private List<Customer> getMockCustomers() {
        Customer customer = getMockCustomer();

        List<Customer> customers = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            customers.add(customer);
        }
        return customers;
    }
}