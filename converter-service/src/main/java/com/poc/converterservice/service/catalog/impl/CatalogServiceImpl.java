package com.poc.converterservice.service.catalog.impl;

import com.crmpoc.catalog.GetAllProductRequest;
import com.crmpoc.catalog.GetProductDetailsRequest;
import com.crmpoc.catalog.OrderFulfilmentRequest;
import com.crmpoc.catalog.ProductDetails;
import com.crmpoc.catalog.SaveProductRequest;
import com.crmpoc.catalog.UpdateProductRequest;
import com.crmpoc.catalog.CatalogPort;
import com.poc.converterservice.service.catalog.CatalogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CatalogPort catalogClient;

    public CatalogServiceImpl(CatalogPort catalogClient) {
        this.catalogClient = catalogClient;
    }

    @Override
    public void createProduct(ProductDetails productDetails) {
        SaveProductRequest request = new SaveProductRequest();
        request.setProductDetails(productDetails);
        catalogClient.saveProduct(request);
    }

    @Override
    public List<ProductDetails> getAllProducts() {
        return catalogClient.getAllProduct(new GetAllProductRequest()).getProductDetails();
    }

    @Override
    public ProductDetails getProductDetails(Long id) {
        GetProductDetailsRequest request = new GetProductDetailsRequest();
        request.setId(id);
        return catalogClient.getProductDetails(request).getProductDetails();
    }

    @Override
    public void updateProduct(ProductDetails productDetails) {
        UpdateProductRequest request = new UpdateProductRequest();
        request.setProductDetails(productDetails);
        catalogClient.updateProduct(request);
    }

    @Override
    public Boolean orderFulfilment(Long id) {
        try {
            OrderFulfilmentRequest request = new OrderFulfilmentRequest();
            request.setProductId(id);
            return catalogClient.orderFulfilment(request).isFulfilment();
        } catch (Exception e) {
            log.error("[catalog-service -> order-fulfilment] : {}", e.getMessage());
            return false;
        }
    }
}
