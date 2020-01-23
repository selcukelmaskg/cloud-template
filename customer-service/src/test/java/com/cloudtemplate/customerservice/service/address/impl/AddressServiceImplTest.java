package com.cloudtemplate.customerservice.service.address.impl;

import com.crmpoc.customer.AddressDetail;
import com.cloudtemplate.customerservice.domain.Address;
import com.cloudtemplate.customerservice.repository.AddressRepository;
import com.cloudtemplate.customerservice.service.address.AddressService;
import com.cloudtemplate.shared.util.ObjectMapperUtils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class AddressServiceImplTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private AddressRepository addressRepository;
    private AddressService addressService;


    @BeforeEach
    void setUp() {
        addressRepository = mock(AddressRepository.class);
        addressService = new AddressServiceImpl(addressRepository);
    }

    @Test
    void findById() {
        AddressDetail addressDetail = getMockAddressDetail();
        when(addressRepository.findById(1L)).thenReturn(Optional.of(ObjectMapperUtils.map(addressDetail, Address.class)));
        when(addressRepository.findById(2L)).thenReturn(Optional.empty());

        Assert.assertEquals(addressDetail.getId(), addressService.findById(1L).getId());
        Assert.assertNull(addressService.findById(2L));
        log.info("[addressService -> findById]: successfully completed !");
    }

    @Test
    void findAll() {
        AddressDetail addressDetail = getMockAddressDetail();
        List<AddressDetail> allAddressDetails = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            allAddressDetails.add(addressDetail);
        }

        List<Address> allAddress = ObjectMapperUtils.mapAll(allAddressDetails, Address.class);

        when(addressRepository.findAll()).thenReturn(allAddress);

        Assert.assertEquals(allAddressDetails.size(), addressService.findAll().size());
        log.info("[addressService -> findAll]: successfully completed !");
    }

    private AddressDetail getMockAddressDetail() {
        AddressDetail mockAdressDetail = new AddressDetail();
        mockAdressDetail.setId(1L);
        mockAdressDetail.setCity("Ankara");
        mockAdressDetail.setCountry("Türkiye");
        mockAdressDetail.setStreet("Mamak");

        return mockAdressDetail;
    }
}