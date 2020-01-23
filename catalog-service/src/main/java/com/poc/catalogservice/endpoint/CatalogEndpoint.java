package com.poc.catalogservice.endpoint;

import com.crmpoc.catalog.GetAllProductRequest;
import com.crmpoc.catalog.GetAllProductResponse;
import com.crmpoc.catalog.GetProductDetailsRequest;
import com.crmpoc.catalog.GetProductDetailsResponse;
import com.crmpoc.catalog.OrderFulfilmentRequest;
import com.crmpoc.catalog.OrderFulfilmentResponse;
import com.crmpoc.catalog.SaveProductRequest;
import com.crmpoc.catalog.UpdateProductRequest;
import com.poc.catalogservice.dto.CustomAllProductResponse;
import com.poc.catalogservice.service.ProductService;
import org.junit.Assert;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import static com.poc.shared.constans.ApplicationConstants.CATALOG_ENDPOINT;

@Endpoint
public class CatalogEndpoint {
    private ProductService productService;

    public CatalogEndpoint(ProductService productService) {
        this.productService = productService;
    }

    @PayloadRoot(namespace = CATALOG_ENDPOINT, localPart = "GetProductDetailsRequest")
    @ResponsePayload
    public GetProductDetailsResponse getProductDetailById(@RequestPayload GetProductDetailsRequest request) {
        GetProductDetailsResponse response = new GetProductDetailsResponse();
        response.setProductDetails(productService.findById(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = CATALOG_ENDPOINT, localPart = "GetAllProductRequest")
    @ResponsePayload
    public GetAllProductResponse getAllProducts(@RequestPayload GetAllProductRequest getAllProductRequest) {
        CustomAllProductResponse response = new CustomAllProductResponse();
        response.setProducts(productService.findAll());
        return response;
    }

    @PayloadRoot(namespace = CATALOG_ENDPOINT, localPart = "SaveProductRequest")
    @ResponsePayload
    public void saveProduct(@RequestPayload SaveProductRequest saveProductRequest) {
        productService.save(saveProductRequest.getProductDetails());
    }

    @PayloadRoot(namespace = CATALOG_ENDPOINT, localPart = "UpdateProductRequest")
    @ResponsePayload
    public void updateProduct(@RequestPayload UpdateProductRequest updateProductRequest) {
        productService.update(updateProductRequest.getProductDetails());
    }

    @PayloadRoot(namespace = CATALOG_ENDPOINT, localPart = "OrderFulfilmentRequest")
    @ResponsePayload
    public OrderFulfilmentResponse orderFulfilment(@RequestPayload OrderFulfilmentRequest orderFulfilmentRequest) {
        Assert.assertTrue(orderFulfilmentRequest.getProductId() > 0);
        OrderFulfilmentResponse response = new OrderFulfilmentResponse();
        response.setFulfilment(productService.orderRegistration(orderFulfilmentRequest.getProductId()));
        return response;
    }
}
