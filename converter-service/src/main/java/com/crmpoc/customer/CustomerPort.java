
package com.crmpoc.customer;

import com.crmpoc.customer.CreateAddressRequest;
import com.crmpoc.customer.CreateCustomerRequest;
import com.crmpoc.customer.CustomerValidationRequest;
import com.crmpoc.customer.CustomerValidationResponse;
import com.crmpoc.customer.GetAddressDetailsRequest;
import com.crmpoc.customer.GetAddressDetailsResponse;
import com.crmpoc.customer.GetAllAddressRequest;
import com.crmpoc.customer.GetAllAddressResponse;
import com.crmpoc.customer.GetAllCustomerRequest;
import com.crmpoc.customer.GetAllCustomerResponse;
import com.crmpoc.customer.GetCustomerDetailsRequest;
import com.crmpoc.customer.GetCustomerDetailsResponse;
import com.crmpoc.customer.ObjectFactory;
import com.crmpoc.customer.UpdateAddressRequest;
import com.crmpoc.customer.UpdateCustomerRequest;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "CustomerPort", targetNamespace = "http://www.crmpoc.com/customer")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CustomerPort {


    /**
     * 
     * @param getCustomerDetailsRequest
     * @return
     *     returns com.crmpoc.customer.GetCustomerDetailsResponse
     */
    @WebMethod(operationName = "GetCustomerDetails")
    @WebResult(name = "GetCustomerDetailsResponse", targetNamespace = "http://www.crmpoc.com/customer", partName = "GetCustomerDetailsResponse")
    public GetCustomerDetailsResponse getCustomerDetails(
        @WebParam(name = "GetCustomerDetailsRequest", targetNamespace = "http://www.crmpoc.com/customer", partName = "GetCustomerDetailsRequest")
                GetCustomerDetailsRequest getCustomerDetailsRequest);

    /**
     * 
     * @param getAllCustomerRequest
     * @return
     *     returns com.crmpoc.customer.GetAllCustomerResponse
     */
    @WebMethod(operationName = "GetAllCustomer")
    @WebResult(name = "GetAllCustomerResponse", targetNamespace = "http://www.crmpoc.com/customer", partName = "GetAllCustomerResponse")
    public GetAllCustomerResponse getAllCustomer(
        @WebParam(name = "GetAllCustomerRequest", targetNamespace = "http://www.crmpoc.com/customer", partName = "GetAllCustomerRequest")
                GetAllCustomerRequest getAllCustomerRequest);

    /**
     * 
     * @param getAllAddressRequest
     * @return
     *     returns com.crmpoc.customer.GetAllAddressResponse
     */
    @WebMethod(operationName = "GetAllAddress")
    @WebResult(name = "GetAllAddressResponse", targetNamespace = "http://www.crmpoc.com/customer", partName = "GetAllAddressResponse")
    public GetAllAddressResponse getAllAddress(
        @WebParam(name = "GetAllAddressRequest", targetNamespace = "http://www.crmpoc.com/customer", partName = "GetAllAddressRequest")
                GetAllAddressRequest getAllAddressRequest);

    /**
     * 
     * @param customerValidationRequest
     * @return
     *     returns com.crmpoc.customer.CustomerValidationResponse
     */
    @WebMethod(operationName = "CustomerValidation")
    @WebResult(name = "CustomerValidationResponse", targetNamespace = "http://www.crmpoc.com/customer", partName = "CustomerValidationResponse")
    public CustomerValidationResponse customerValidation(
        @WebParam(name = "CustomerValidationRequest", targetNamespace = "http://www.crmpoc.com/customer", partName = "CustomerValidationRequest")
                CustomerValidationRequest customerValidationRequest);

    /**
     * 
     * @param updateCustomerRequest
     */
    @WebMethod(operationName = "UpdateCustomer")
    @Oneway
    public void updateCustomer(
        @WebParam(name = "UpdateCustomerRequest", targetNamespace = "http://www.crmpoc.com/customer", partName = "UpdateCustomerRequest")
                UpdateCustomerRequest updateCustomerRequest);

    /**
     * 
     * @param getAddressDetailsRequest
     * @return
     *     returns com.crmpoc.customer.GetAddressDetailsResponse
     */
    @WebMethod(operationName = "GetAddressDetails")
    @WebResult(name = "GetAddressDetailsResponse", targetNamespace = "http://www.crmpoc.com/customer", partName = "GetAddressDetailsResponse")
    public GetAddressDetailsResponse getAddressDetails(
        @WebParam(name = "GetAddressDetailsRequest", targetNamespace = "http://www.crmpoc.com/customer", partName = "GetAddressDetailsRequest")
                GetAddressDetailsRequest getAddressDetailsRequest);

    /**
     * 
     * @param createAddressRequest
     */
    @WebMethod(operationName = "CreateAddress")
    @Oneway
    public void createAddress(
        @WebParam(name = "CreateAddressRequest", targetNamespace = "http://www.crmpoc.com/customer", partName = "CreateAddressRequest")
                CreateAddressRequest createAddressRequest);

    /**
     * 
     * @param createCustomerRequest
     */
    @WebMethod(operationName = "CreateCustomer")
    @Oneway
    public void createCustomer(
        @WebParam(name = "CreateCustomerRequest", targetNamespace = "http://www.crmpoc.com/customer", partName = "CreateCustomerRequest")
                CreateCustomerRequest createCustomerRequest);

    /**
     * 
     * @param updateAddressRequest
     */
    @WebMethod(operationName = "UpdateAddress")
    @Oneway
    public void updateAddress(
        @WebParam(name = "UpdateAddressRequest", targetNamespace = "http://www.crmpoc.com/customer", partName = "UpdateAddressRequest")
                UpdateAddressRequest updateAddressRequest);

}