package com.cloudtemplate.customerservice.service.address.impl;

import com.cloudtemplate.customerservice.domain.Address;
import com.cloudtemplate.customerservice.repository.AddressRepository;
import com.cloudtemplate.customerservice.service.address.AddressService;
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
        Address address = getMockAddress();
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(addressRepository.findById(2L)).thenReturn(Optional.empty());

        Assert.assertEquals(address.getId(), addressService.findById(1L).getId());
        Assert.assertNull(addressService.findById(2L));
        log.info("[addressService -> findById]: successfully completed !");
    }

    @Test
    void findAll() {
        List<Address> allAddresses = getMockAddresses();

        when(addressRepository.findAll()).thenReturn(allAddresses);

        Assert.assertEquals(allAddresses, addressService.findAll());
        log.info("[addressService -> findAll]: successfully completed !");
    }

    private Address getMockAddress() {
        Address mockAddress = new Address();
        mockAddress.setId(1L);
        mockAddress.setCity("Ankara");
        mockAddress.setCountry("Türkiye");
        mockAddress.setStreet("Mamak");

        return mockAddress;
    }

    private List<Address> getMockAddresses() {
        Address address = getMockAddress();
        List<Address> addresses = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            addresses.add(address);
        }

        return addresses;
    }
}