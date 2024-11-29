package com.UserManager.model.dto.address;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateAddressDto {


    @NotBlank
    //@Pattern( regexp = "^(\\d{1,}) [a-zA-Z0-9\\s]+(\\,)? [a-zA-Z]+(\\,)? [A-Z]{2} [0-9]{5,6}$")
    private String address;

    @NotBlank
    private String country;

    @NotBlank
    private String state;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    @NotBlank
    //@Pattern(regexp = "^[0-9]{5}(?:-[0-9]{4})?$")
    private String postalCode;

    @NotBlank
    //@Pattern(regexp = "^\\+?\\d{1,4}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$")
    private String phone;
}
