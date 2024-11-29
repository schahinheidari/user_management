package com.UserManager.controller;

import com.UserManager.api.AddressAPI;
import com.UserManager.exception.NotFoundException;
import com.UserManager.model.dto.address.AddAddressDto;
import com.UserManager.model.dto.address.UpdateAddressDto;
import com.UserManager.model.dto.address.ViewAddressDto;
import com.UserManager.model.entites.Address;
import com.UserManager.model.mapper.AddressMapper;
import com.UserManager.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AddressController implements AddressAPI {

    private final AddressService addressService;
    private final AddressMapper mapper;

    @Override
    public ResponseEntity<ViewAddressDto> save(@RequestBody AddAddressDto addAddressDto) {
        Address address = mapper.mapAddAddressDtoToAddress(addAddressDto);
        Address savedAddress = addressService.save(address);
        ViewAddressDto viewAddressDto = mapper.mapAddressToViewAddressDto(savedAddress);
        return new ResponseEntity<>(viewAddressDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ViewAddressDto> update(
            @PathVariable Long id,
            @RequestBody UpdateAddressDto updateAddressDto) {
        Address address = mapper.mapUpdateAddressDtoToAddress(updateAddressDto);
        address.setId(id);
        Address updatedAddress = addressService.update(address);
        ViewAddressDto viewAddressDto = mapper.mapAddressToViewAddressDto(updatedAddress);
        return new ResponseEntity<>(viewAddressDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ViewAddressDto> findById(@PathVariable Long id) {
        try {
            Address address = addressService.findById(id);
            ViewAddressDto dto = mapper.mapAddressToViewAddressDto(address);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<ViewAddressDto>> findAll() {
        List<Address> addresses = addressService.findAll();
        List<ViewAddressDto> viewAddressDtos = mapper.mapAddressListToViewAddressDtoList(addresses);
        return new ResponseEntity<>(viewAddressDtos, HttpStatus.OK);
    }
}
