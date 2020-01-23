package com.poc.converterservice.controller;

import com.crmpoc.customer.AddressDetail;
import com.poc.converterservice.service.address.AddressService;
import org.junit.Assert;
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
    public static final String END_POINT = "/api/address";

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<AddressDetail> getAllAddress() {
        return addressService.getAllAddress();
    }

    @GetMapping("/{id}")
    public AddressDetail getAddressDetails(@PathVariable(name = "id") Long id) {
        Assert.assertTrue(id > 0);
        return addressService.getAddressDetails(id);
    }

    @PostMapping
    public void createAddress(@RequestBody AddressDetail addressDetail) {
        addressService.createAddress(addressDetail);
    }

    @PutMapping
    public void updateAddress(@RequestBody AddressDetail addressDetail) {
        addressService.updateAddress(addressDetail);
    }
}
