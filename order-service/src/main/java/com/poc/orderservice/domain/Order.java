package com.poc.orderservice.domain;

import com.poc.shared.enumeration.OrderStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

import static com.poc.shared.constans.ApplicationConstants.ZONE_ID;

@Document(collection = "order")
public class Order implements Serializable {
    private static final long serialVersionUID = -8606500473779089479L;

    @Id
    @GeneratedValue
    private String id;
    @Positive
    private Long tckn;
    @Positive
    private Long productId;
    private LocalDateTime createDate;
    private String systemMessage;
    private OrderStatus status;

    public Order() {
        if (id == null) {
            this.createDate = LocalDateTime.now();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTckn() {
        return tckn;
    }

    public void setTckn(Long tckn) {
        this.tckn = tckn;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getCreateDate() {
        return ZonedDateTime.of(this.createDate, ZoneId.of(ZONE_ID)).toString();
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getSystemMessage() {
        return systemMessage;
    }

    public void setSystemMessage(String systemMessage) {
        this.systemMessage = systemMessage;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", tckn=" + tckn +
                ", productId=" + productId +
                ", createDate=" + createDate +
                ", status=" + status +
                ", systemMessage=" + systemMessage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId().equals(order.getId()) &&
                getTckn().equals(order.getTckn()) &&
                getProductId().equals(order.getProductId()) &&
                getCreateDate().equals(order.getCreateDate()) &&
                getSystemMessage().equals(order.getSystemMessage()) &&
                getStatus() == order.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTckn(), getProductId(), getCreateDate(), getStatus(), getSystemMessage());
    }
}
