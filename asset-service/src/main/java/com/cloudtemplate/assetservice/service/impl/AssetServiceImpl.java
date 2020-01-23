package com.cloudtemplate.assetservice.service.impl;

import com.cloudtemplate.assetservice.domain.Asset;
import com.cloudtemplate.assetservice.service.AssetService;
import com.cloudtemplate.assetservice.client.feign.OrderClient;
import com.cloudtemplate.assetservice.repository.AssetRepository;
import com.cloudtemplate.shared.util.IpAddressUtil;
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
        if (Boolean.TRUE.equals(orderClient.validateOrder(orderId))) {
            String ip = IpAddressUtil.generateIp();
            if (Boolean.FALSE.equals(repository.existsAssetByIp(ip))) {
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
        log.info("[assetSaved]: {}", asset);
        repository.save(asset);
    }

    @Override
    @Cacheable(key = "#orderId")
    public Asset findByOrderId(String orderId) {
        return repository.findFirstByOrderId(orderId);
    }
}
