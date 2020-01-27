package com.cloudtemplate.assetservice.controller;

import com.cloudtemplate.assetservice.domain.Asset;
import com.cloudtemplate.assetservice.service.AssetService;
import com.cloudtemplate.shared.dto.asset.AssetBindingRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "Asset Controller", description = "Asset CRUD operations")
public class AssetController {
    static final String END_POINT = "/api/asset";

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @ApiOperation(value = "IP address provider in order fulfilment", response = String.class)
    @PostMapping("/binding")
    public String assetBinding(@RequestBody @Valid AssetBindingRequest assetBindingRequest) {
        return assetService.ipBinding(assetBindingRequest.getOrderId());
    }

    @ApiOperation(value = "IP address list", response = List.class)
    @GetMapping
    public List<Asset> findAll() {
        return assetService.findAll();
    }

    @ApiOperation(value = "Find Asset by order ID")
    @GetMapping("/order/{orderId}")
    public Asset findByOrderId(@PathVariable(name = "orderId") String orderId) {
        Assert.assertFalse("OrderId must be not null !", orderId.isEmpty());
        return assetService.findByOrderId(orderId);
    }
}
