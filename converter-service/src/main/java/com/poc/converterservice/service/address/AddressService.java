package com.poc.converterservice.service.address;

import com.crmpoc.customer.AddressDetail;
import com.crmpoc.customer.CreateAddressRequest;
import com.crmpoc.customer.GetAddressDetailsRequest;
import com.crmpoc.customer.GetAddressDetailsResponse;
import com.crmpoc.customer.GetAllAddressResponse;
import com.crmpoc.customer.UpdateAddressRequest;

import java.util.List;

public interface AddressService {
    List<AddressDetail> getAllAddress();

    void createAddress(AddressDetail addressDetail);

    void updateAddress(AddressDetail addressDetail);

    AddressDetail getAddressDetails(Long id);
}
