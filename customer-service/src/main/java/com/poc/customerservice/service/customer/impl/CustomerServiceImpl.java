package com.poc.customerservice.service.customer.impl;

import com.crmpoc.customer.CustomerDetails;
import com.poc.customerservice.domain.Customer;
import com.poc.customerservice.repository.CustomerRepository;
import com.poc.customerservice.service.customer.CustomerService;
import com.poc.shared.util.ObjectMapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "customer")
public class CustomerServiceImpl implements CustomerService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable(key = "#tckn")
    public CustomerDetails findById(Long tckn) {
        return ObjectMapperUtils.map(repository.findById(tckn).orElse(null), CustomerDetails.class);
    }

    @Override
    @Cacheable
    public List<CustomerDetails> findAll() {
        return ObjectMapperUtils.mapAll(repository.findAll(), CustomerDetails.class);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void save(CustomerDetails customerDetails) {
        Customer customer = repository.save(ObjectMapperUtils.map(customerDetails, Customer.class));
        log.info("[customerSaved]: Clear address cache !");
        log.info("[customerSaved]: Data:\n {}", customer);
        log.info("[customerSaved]: Send Mail : {}", customer.getMail());
    }

    @Override
    @CacheEvict(allEntries = true)
    public void update(CustomerDetails customerDetails) {
        if (!repository.existsById(customerDetails.getTckn())) {
            log.warn("tckn: {} customer not found.", customerDetails.getTckn());
            return;
        }

        save(customerDetails);
    }

    @Override
    public Boolean existInCustomers(Long tckn) {
        return findById(tckn) != null;
    }
}
