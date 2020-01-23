package com.poc.assetservice.repository;

import com.poc.assetservice.domain.Asset;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AssetRepository extends MongoRepository<Asset, String > {
    Boolean existsAssetByIp(String ip);

    Asset findFirstByOrderId(String orderId);
}
