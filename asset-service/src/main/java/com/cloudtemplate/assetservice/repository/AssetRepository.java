package com.cloudtemplate.assetservice.repository;

import com.cloudtemplate.assetservice.domain.Asset;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AssetRepository extends MongoRepository<Asset, String > {
    Boolean existsAssetByIp(String ip);

    Asset findFirstByOrderId(String orderId);
}
