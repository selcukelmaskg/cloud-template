package com.cloudtemplate.customerservice.service.customer.impl;


import com.crmpoc.customer.AddressDetail;
import com.crmpoc.customer.CustomerDetails;
import com.cloudtemplate.customerservice.domain.Customer;
import com.cloudtemplate.customerservice.repository.CustomerRepository;
import com.cloudtemplate.customerservice.service.customer.CustomerService;
import com.cloudtemplate.shared.util.ObjectMapperUtils;
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
        CustomerDetails customerDetails = getMockCustomerDetail();

        when(customerRepository.findById(1L)).thenReturn(Optional.of(ObjectMapperUtils.map(customerDetails, Customer.class)));
        when(customerRepository.findById(2L)).thenReturn(Optional.empty());

        Assert.assertEquals(customerDetails.getTckn(), customerService.findById(1L).getTckn());
        Assert.assertNull(customerService.findById(2L));
        log.info("[customerService -> findById]: successfully completed !");
    }

    @Test
    public void findAll() {
        CustomerDetails customerDetails = getMockCustomerDetail();
        List<CustomerDetails> allCustomerDetails = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            allCustomerDetails.add(customerDetails);
        }

        List<Customer> allCustomer = ObjectMapperUtils.mapAll(allCustomerDetails, Customer.class);

        when(customerRepository.findAll()).thenReturn(allCustomer);

        Assert.assertEquals(allCustomerDetails.size(), customerService.findAll().size());
        log.info("[customerService -> findAll]: successfully completed !");
    }

    private CustomerDetails getMockCustomerDetail() {
        CustomerDetails mockProductDetail = new CustomerDetails();
        mockProductDetail.setTckn(Long.parseLong("67204082123"));
        mockProductDetail.setName("lol");
        mockProductDetail.setSurname("lul");
        mockProductDetail.setBirthYear(1990);

        AddressDetail addressDetail = new AddressDetail();
        addressDetail.setId(Long.parseLong("1"));
        addressDetail.setCity("Istanbul");
        addressDetail.setCountry("Türkiye");
        addressDetail.setStreet("Bağdat");

        mockProductDetail.setAddress(addressDetail);

        return mockProductDetail;
    }
}