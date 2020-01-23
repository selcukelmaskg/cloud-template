package com.cloudtemplate.customerservice.dto;

import com.crmpoc.customer.CustomerDetails;
import com.crmpoc.customer.GetAllCustomerResponse;

import java.util.List;

public class CustomAllCustomerResponse extends GetAllCustomerResponse {
    public void setAddresses(List<CustomerDetails> addresses) {
        this.addresses = addresses;
    }
}
