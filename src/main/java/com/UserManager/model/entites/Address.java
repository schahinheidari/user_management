package com.UserManager.model.entites;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    /*@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;*/
}
