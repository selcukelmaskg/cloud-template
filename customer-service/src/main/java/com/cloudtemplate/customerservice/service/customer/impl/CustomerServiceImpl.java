package com.cloudtemplate.customerservice.service.customer.impl;

import com.cloudtemplate.customerservice.domain.Customer;
import com.cloudtemplate.customerservice.repository.CustomerRepository;
import com.cloudtemplate.customerservice.service.customer.CustomerService;
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
    public Customer findById(Long tckn) {
        return repository.findById(tckn).orElse(null);
    }

    @Override
    @Cacheable
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    @CacheEvict(allEntries = true)
    public void save(Customer customer) {
        Customer savedCustomer = repository.save(customer);
        log.info("[customerSaved]: Clear address cache !");
        log.info("[customerSaved]: Data:\n {}", savedCustomer);
        log.info("[customerSaved]: Send Mail : {}", savedCustomer.getMail());
    }

    @Override
    @CacheEvict(allEntries = true)
    public void update(Customer customer) {
        if (!repository.existsById(customer.getTckn())) {
            log.warn("tckn: {} customer not found.", customer.getTckn());
            return;
        }

        save(customer);
    }

    @Override
    public Boolean existInCustomers(Long tckn) {
        return findById(tckn) != null;
    }
}
