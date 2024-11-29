package com.UserManager.model.dto.address;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ViewAddressDto {
    private String address;

    private String country;

    private String state;

    private String city;

    private String street;

    private String postalCode;

    private String phone;
}
