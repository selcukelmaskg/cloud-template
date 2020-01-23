package com.poc.assetservice.service.impl;

import com.poc.assetservice.client.feign.OrderClient;
import com.poc.assetservice.domain.Asset;
import com.poc.assetservice.repository.AssetRepository;
import com.poc.assetservice.service.AssetService;
import com.poc.shared.util.IpAddressUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "asset")
public class AssetServiceImpl implements AssetService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final AssetRepository repository;
    private final OrderClient orderClient;

    public AssetServiceImpl(AssetRepository repository,
                            OrderClient orderClient) {
        this.repository = repository;
        this.orderClient = orderClient;
    }

    @Override
    public String ipBinding(String orderId) {
        if (orderClient.validateOrder(orderId)) {
            String ip = IpAddressUtil.generateIp();
            if (!repository.existsAssetByIp(ip)) {
                save(new Asset(orderId, ip));
                return ip;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    @Cacheable
    public List<Asset> findAll() {
        return repository.findAll();
    }

    @Override
    @CacheEvict(allEntries = true)
    public void save(Asset asset) {
        log.info("[assetSaved]: {}", asset.toString());
        repository.save(asset);
    }

    @Override
    @Cacheable(key = "#orderId")
    public Asset findByOrderId(String orderId) {
        return repository.findFirstByOrderId(orderId);
    }
}
