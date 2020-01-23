package com.cloudtemplate.customerservice.controller;

import com.cloudtemplate.customerservice.domain.Address;
import com.cloudtemplate.customerservice.service.address.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AddressController.END_POINT)
public class AddressController {
    static final String END_POINT = "/api/address";
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public void save(@RequestBody Address address) {
        addressService.save(address);
    }

    @GetMapping
    public List<Address> allAddresses() {
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public Address getAddressDetailById(@PathVariable(name = "id") Long id) {
        return addressService.findById(id);
    }

    @PutMapping
    public void updateAddress(@RequestBody Address address) {
        addressService.update(address);
    }
}
