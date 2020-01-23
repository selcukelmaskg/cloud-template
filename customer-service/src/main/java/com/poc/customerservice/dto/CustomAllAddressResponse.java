package com.poc.customerservice.dto;

import com.crmpoc.customer.AddressDetail;
import com.crmpoc.customer.GetAllAddressResponse;

import java.util.List;

public class CustomAllAddressResponse extends GetAllAddressResponse {
    public void setAddresses(List<AddressDetail> addresses) {
        this.addresses = addresses;
    }
}
