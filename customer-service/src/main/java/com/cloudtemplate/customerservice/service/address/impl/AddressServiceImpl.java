package com.cloudtemplate.customerservice.service.address.impl;

import com.cloudtemplate.customerservice.domain.Address;
import com.cloudtemplate.customerservice.repository.AddressRepository;
import com.cloudtemplate.customerservice.service.address.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "address")
public class AddressServiceImpl implements AddressService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final AddressRepository repository;

    public AddressServiceImpl(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable(key = "#id")
    public Address findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Cacheable
    public List<Address> findAll() {
        return repository.findAll();
    }

    @Override
    @CacheEvict(allEntries = true)
    public void save(Address address) {
        Address savedAddress = repository.save(address);
        log.info("[addressSaved]: Clear address cache !");
        log.info("[addressSaved]: Data:\n {}", savedAddress);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void update(Address addressDetail) {
        if (!repository.existsById(addressDetail.getId())) {
            log.warn("id: {} address not found.", addressDetail.getId());
            return;
        }

        save(addressDetail);
    }
}
