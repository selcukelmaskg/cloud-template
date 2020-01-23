package com.cloudtemplate.customerservice.service.address;

import com.cloudtemplate.customerservice.domain.Address;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface AddressService {
    Address findById(Long id);

    List<Address> findAll();

    @Async
    void save(Address address);

    @Async
    void update(Address address);
}
