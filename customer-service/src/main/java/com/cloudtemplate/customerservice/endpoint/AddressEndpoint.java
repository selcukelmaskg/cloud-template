package com.cloudtemplate.customerservice.endpoint;

import com.cloudtemplate.customerservice.dto.CustomAllAddressResponse;
import com.cloudtemplate.customerservice.service.address.AddressService;
import com.crmpoc.customer.CreateAddressRequest;
import com.crmpoc.customer.GetAddressDetailsRequest;
import com.crmpoc.customer.GetAddressDetailsResponse;
import com.crmpoc.customer.GetAllAddressRequest;
import com.crmpoc.customer.GetAllAddressResponse;
import com.crmpoc.customer.UpdateAddressRequest;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import static com.cloudtemplate.shared.constans.ApplicationConstants.CUSTOMER_ENDPOINT;

@Endpoint
@CrossOrigin
public class AddressEndpoint {
    private final AddressService addressService;

    public AddressEndpoint(AddressService addressService) {
        this.addressService = addressService;
    }

    @PayloadRoot(namespace = CUSTOMER_ENDPOINT, localPart = "CreateAddressRequest")
    @ResponsePayload
    public void createAddress(@RequestPayload CreateAddressRequest createAddressRequest) {
        addressService.save(createAddressRequest.getAddress());
    }

    @PayloadRoot(namespace = CUSTOMER_ENDPOINT, localPart = "GetAllAddressRequest")
    @ResponsePayload
    public GetAllAddressResponse allAddresses(@RequestPayload GetAllAddressRequest getAllAddressRequest) {
        CustomAllAddressResponse response = new CustomAllAddressResponse();
        response.setAddresses(addressService.findAll());
        return response;
    }

    @PayloadRoot(namespace = CUSTOMER_ENDPOINT, localPart = "GetAddressDetailsRequest")
    @ResponsePayload
    public GetAddressDetailsResponse getAddressDetailById(@RequestPayload GetAddressDetailsRequest getAddressDetailRequest) {
        GetAddressDetailsResponse response = new GetAddressDetailsResponse();
        response.setAddressDetails(addressService.findById(getAddressDetailRequest.getId()));
        return response;
    }

    @PayloadRoot(namespace = CUSTOMER_ENDPOINT, localPart = "UpdateAddressRequest")
    @ResponsePayload
    public void updateAddress(@RequestPayload UpdateAddressRequest updateAddressRequest) throws NotFoundException {
        addressService.update(updateAddressRequest.getAddress());
    }
}
