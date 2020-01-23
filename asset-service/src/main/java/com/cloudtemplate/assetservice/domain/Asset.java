package com.cloudtemplate.assetservice.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@ToString
@EqualsAndHashCode
@Document(collection = "asset")
public class Asset {
    @Id
    @GeneratedValue
    private String id;

    private String orderId;
    private String ip;

    public Asset(String orderId, String ip) {
        this.orderId = orderId;
        this.ip = ip;
    }
}
