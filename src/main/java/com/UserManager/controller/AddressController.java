package com.UserManager.controller;

import com.UserManager.model.entites.Address;
import com.UserManager.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address/v1/")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Address save(@RequestBody Address address) {
        return addressService.save(address);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Address update(@RequestBody Address address) {
        return addressService.update(address);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        addressService.delete(id);
    }

    @GetMapping("/find/{id}")
    public Address findById(@PathVariable Long id) {
        return addressService.findById(id);
    }

    @GetMapping("/list")
    public List<Address> findAll() {
        return addressService.findAll();
    }
}
