package com.poc.assetservice.controller;

import com.poc.assetservice.domain.Asset;
import com.poc.assetservice.service.AssetService;
import com.poc.shared.dto.asset.AssetBindingRequest;
import org.junit.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(AssetController.END_POINT)
public class AssetController {
    static final String END_POINT = "/api/asset";

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @PostMapping("/binding")
    public String assetBinding(@RequestBody @Valid AssetBindingRequest assetBindingRequest) {
        return assetService.ipBinding(assetBindingRequest.getOrderId());
    }

    @GetMapping
    public List<Asset> findAll() {
        return assetService.findAll();
    }

    @GetMapping("/order/{orderId}")
    public Asset findByOrderId(@PathVariable(name = "orderId") String orderId) {
        Assert.assertFalse("OrderId must be not null !", orderId.isEmpty());
        return assetService.findByOrderId(orderId);
    }
}
