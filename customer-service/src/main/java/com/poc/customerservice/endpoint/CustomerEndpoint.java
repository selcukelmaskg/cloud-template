package com.poc.customerservice.endpoint;

import com.crmpoc.customer.CreateCustomerRequest;
import com.crmpoc.customer.CustomerValidationRequest;
import com.crmpoc.customer.CustomerValidationResponse;
import com.crmpoc.customer.GetAllCustomerRequest;
import com.crmpoc.customer.GetAllCustomerResponse;
import com.crmpoc.customer.GetCustomerDetailsRequest;
import com.crmpoc.customer.GetCustomerDetailsResponse;
import com.crmpoc.customer.UpdateCustomerRequest;
import com.poc.customerservice.dto.CustomAllCustomerResponse;
import com.poc.customerservice.service.customer.CustomerService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import static com.poc.shared.constans.ApplicationConstants.CUSTOMER_ENDPOINT;

@Endpoint
@CrossOrigin
public class CustomerEndpoint {
    private final CustomerService customerService;

    public CustomerEndpoint(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PayloadRoot(namespace = CUSTOMER_ENDPOINT, localPart = "GetCustomerDetailsRequest")
    @ResponsePayload
    public GetCustomerDetailsResponse getCustomerDetailById(@RequestPayload GetCustomerDetailsRequest request) {
        GetCustomerDetailsResponse response = new GetCustomerDetailsResponse();
        response.setCustomerDetails(customerService.findById(request.getTckn()));
        return response;
    }

    @PayloadRoot(namespace = CUSTOMER_ENDPOINT, localPart = "CreateCustomerRequest")
    @ResponsePayload
    public void createCustomer(@RequestPayload CreateCustomerRequest createCustomerRequest) {
        customerService.save(createCustomerRequest.getCustomerDetails());
    }

    @PayloadRoot(namespace = CUSTOMER_ENDPOINT, localPart = "GetAllCustomerRequest")
    @ResponsePayload
    public GetAllCustomerResponse allCustomers(@RequestPayload GetAllCustomerRequest getAllCustomerRequest) {
        CustomAllCustomerResponse response = new CustomAllCustomerResponse();
        response.setAddresses(customerService.findAll());
        return response;
    }

    @PayloadRoot(namespace = CUSTOMER_ENDPOINT, localPart = "UpdateCustomerRequest")
    @ResponsePayload
    public void updateCustomer(@RequestPayload UpdateCustomerRequest updateCustomerRequest) {
        customerService.update(updateCustomerRequest.getCustomer());
    }

    @PayloadRoot(namespace = CUSTOMER_ENDPOINT, localPart = "CustomerValidationRequest")
    @ResponsePayload
    public CustomerValidationResponse validateCustomer(@RequestPayload CustomerValidationRequest customerValidationRequest) {
        CustomerValidationResponse response = new CustomerValidationResponse();
        response.setValidate(customerService.existInCustomers(customerValidationRequest.getTckn()));
        return response;
    }
}
