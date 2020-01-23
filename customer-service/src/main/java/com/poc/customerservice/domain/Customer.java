package com.poc.customerservice.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    private static final long serialVersionUID = -6789480566376921553L;

    @Id
    private Long tckn;
    private String name;
    private String surname;
    private String mail;
    private int birthYear;

    @ManyToOne
    @JoinColumn(name = "fk_address")
    private Address address;
}
