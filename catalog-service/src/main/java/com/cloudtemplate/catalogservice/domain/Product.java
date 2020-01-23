package com.cloudtemplate.catalogservice.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Entity
@ToString
@Table(name = "product")
public class Product implements Serializable {
    private static final long serialVersionUID = -2352517673314430834L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private float price;
    private String currency;
    private Long stock;
    private boolean enable;
}
