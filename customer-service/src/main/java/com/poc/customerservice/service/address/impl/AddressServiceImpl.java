package com.poc.customerservice.service.address.impl;

import com.crmpoc.customer.AddressDetail;
import com.poc.customerservice.domain.Address;
import com.poc.customerservice.repository.AddressRepository;
import com.poc.customerservice.service.address.AddressService;
import com.poc.shared.util.ObjectMapperUtils;
import javassist.NotFoundException;
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
    public AddressDetail findById(Long id) {
        return ObjectMapperUtils.map(repository.findById(id).orElse(null), AddressDetail.class);
    }

    @Override
    @Cacheable
    public List<AddressDetail> findAll() {
        return ObjectMapperUtils.mapAll(repository.findAll(), AddressDetail.class);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void save(AddressDetail addressDetail) {
        Address address = repository.save(ObjectMapperUtils.map(addressDetail, Address.class));
        log.info("[addressSaved]: Clear address cache !");
        log.info("[addressSaved]: Data:\n {}", address);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void update(AddressDetail addressDetail) throws NotFoundException {
        if (!repository.existsById(addressDetail.getId())) {
            log.warn("id: {} address not found.", addressDetail.getId());
            return;
        }

        save(addressDetail);
    }
}
