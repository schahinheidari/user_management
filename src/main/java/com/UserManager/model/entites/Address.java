package com.UserManager.model.entites;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address extends BaseEntity {

    private String address;

    private String country;

    private String state;

    private String city;

    private String street;

    private String postalCode;

    private String phone;

}
