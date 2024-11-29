package com.UserManager.model.mapper;

import com.UserManager.model.dto.address.AddAddressDto;
import com.UserManager.model.dto.address.UpdateAddressDto;
import com.UserManager.model.dto.address.ViewAddressDto;
import com.UserManager.model.entites.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AddressMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    public abstract Address mapAddAddressDtoToAddress(AddAddressDto addAddressDto);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    public abstract Address mapUpdateAddressDtoToAddress(UpdateAddressDto updateAddressDto);

    public abstract ViewAddressDto mapAddressToViewAddressDto(Address address);

    public abstract List<ViewAddressDto> mapAddressListToViewAddressDtoList(List<Address> addressList);
}
