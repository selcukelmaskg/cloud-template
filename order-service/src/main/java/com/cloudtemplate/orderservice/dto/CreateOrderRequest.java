package com.cloudtemplate.orderservice.dto;

import lombok.Data;

import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
public class CreateOrderRequest implements Serializable {
    private static final long serialVersionUID = -8606500124599089479L;

    @Positive
    private Long tckn;
    @Positive
    private Long productId;
}
