package com.poc.loggerservice.domain.base;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.Objects;

public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -2895683599294993741L;

    @Id
    @GeneratedValue
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaseEntity baseEntity = (BaseEntity) o;
        if (baseEntity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), baseEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
