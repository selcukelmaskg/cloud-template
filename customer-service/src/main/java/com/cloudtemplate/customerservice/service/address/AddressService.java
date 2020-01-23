package com.cloudtemplate.customerservice.service.address;

import com.crmpoc.customer.AddressDetail;
import javassist.NotFoundException;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface AddressService {
    AddressDetail findById(Long id);

    List<AddressDetail> findAll();

    @Async
    void save(AddressDetail addressDetail);

    @Async
    void update(AddressDetail addressDetail) throws NotFoundException;
}
