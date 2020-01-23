package com.poc.converterservice.service.address.impl;

import com.crmpoc.customer.AddressDetail;
import com.crmpoc.customer.CreateAddressRequest;
import com.crmpoc.customer.CustomerPort;
import com.crmpoc.customer.GetAddressDetailsRequest;
import com.crmpoc.customer.GetAllAddressRequest;
import com.crmpoc.customer.UpdateAddressRequest;
import com.poc.converterservice.service.address.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private final CustomerPort customerClient;

    public AddressServiceImpl(CustomerPort customerClient) {
        this.customerClient = customerClient;
    }

    @Override
    public List<AddressDetail> getAllAddress() {
        return customerClient.getAllAddress(new GetAllAddressRequest()).getAddresses();
    }

    @Override
    public void createAddress(AddressDetail addressDetail) {
        CreateAddressRequest request = new CreateAddressRequest();
        request.setAddress(addressDetail);
        customerClient.createAddress(request);
    }

    @Override
    public void updateAddress(AddressDetail addressDetail) {
        UpdateAddressRequest request = new UpdateAddressRequest();
        request.setAddress(addressDetail);
        customerClient.updateAddress(request);
    }

    @Override
    public AddressDetail getAddressDetails(Long id) {
        GetAddressDetailsRequest request = new GetAddressDetailsRequest();
        request.setId(id);
        return customerClient.getAddressDetails(request).getAddressDetails();
    }
}
