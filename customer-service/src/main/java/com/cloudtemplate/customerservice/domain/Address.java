package com.cloudtemplate.customerservice.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "address")
public class Address implements Serializable {
    private static final long serialVersionUID = 1104855776885211921L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @NotEmpty
    private String city;
    @NotBlank
    @NotEmpty
    private String country;
    @NotBlank
    @NotEmpty
    private String street;
}
