package com.cloudtemplate.shared.dto.asset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetBindingRequest {
    @NonNull
    private String orderId;
}
