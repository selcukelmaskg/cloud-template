package com.poc.assetservice.service;

import com.poc.assetservice.domain.Asset;

import java.util.List;

public interface AssetService {
    String ipBinding(String orderId);

    List<Asset> findAll();

    void save(Asset asset);

    Asset findByOrderId(String orderId);
}
