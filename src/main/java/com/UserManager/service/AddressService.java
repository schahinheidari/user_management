package com.UserManager.service;

import com.UserManager.dao.AddressRepository;
import com.UserManager.exception.ConflictException;
import com.UserManager.exception.NotFoundException;
import com.UserManager.model.entites.Address;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public Address save(Address address) {
        Optional<Address> addressOptional = addressRepository.findById(address.getId());
        if (addressOptional.isPresent()) {
            throw new ConflictException("Address already exists");
        }
        return addressRepository.save(address);
    }

    public Address update(Address address) {
        findById(address.getId());
        return addressRepository.save(address);
    }
    public Address findById(Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isEmpty()) {
            throw new NotFoundException("Address not found");
        }
        return addressOptional.get();
    }
    public void delete(Long id) {
        findById(id);
        addressRepository.deleteById(id);
    }
    public List<Address> findAll() {
        return addressRepository.findAll();
    }
}
