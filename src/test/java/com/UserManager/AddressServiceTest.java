package com.UserManager;

import com.UserManager.dao.AddressRepository;
import com.UserManager.exception.NotFoundException;
import com.UserManager.model.entites.Address;
import com.UserManager.service.AddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    @Test
    public void testSaveAddressSuccess() {
        Address address = new Address();
        address.setId(1L);

        when(addressRepository.save(any(Address.class))).thenReturn(address);

        Address savedAddress = addressService.save(address);

        assertNotNull(savedAddress);
        verify(addressRepository).save(address);
    }

    @Test
    public void testUpdateAddressSuccess() {
        Address address = new Address();
        address.setId(1L);

        when(addressRepository.findById(address.getId())).thenReturn(Optional.of(address));
        when(addressRepository.save(address)).thenReturn(address);

        Address updatedAddress = addressService.update(address);

        assertNotNull(updatedAddress);
        verify(addressRepository).save(address);
    }

    @Test
    public void testUpdateAddressNotFound() {
        Address address = new Address();
        address.setId(1L);

        when(addressRepository.findById(address.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> addressService.update(address));
        verify(addressRepository, never()).save(any());
    }

    @Test
    public void testFindByIdSuccess() {
        Long addressId = 1L;
        Address address = new Address();
        address.setId(addressId);

        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));

        Address foundAddress = addressService.findById(addressId);

        assertNotNull(foundAddress);
        assertEquals(addressId, foundAddress.getId());
    }

    @Test
    public void testFindByIdNotFound() {
        Long addressId = 1L;

        when(addressRepository.findById(addressId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> addressService.findById(addressId));
    }

    @Test
    public void testDeleteAddressSuccess() {
        Long addressId = 1L;
        Address address = new Address();
        address.setId(addressId);

        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));

        addressService.delete(addressId);

        verify(addressRepository).deleteById(addressId);
    }

    @Test
    public void testDeleteAddressNotFound() {
        Long addressId = 1L;

        when(addressRepository.findById(addressId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> addressService.delete(addressId));
        verify(addressRepository, never()).deleteById(addressId);
    }

    @Test
    public void testFindAllAddresses() {
        List<Address> addresses = List.of(new Address(), new Address());

        when(addressRepository.findAll()).thenReturn(addresses);

        List<Address> foundAddresses = addressService.findAll();

        assertEquals(2, foundAddresses.size());
        verify(addressRepository).findAll();
    }

    @Test
    public void testPagingAddresses() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Address> addressPage = new PageImpl<>(List.of(new Address(), new Address()));

        when(addressRepository.findAll(pageable)).thenReturn(addressPage);

        Page<Address> resultPage = addressService.paging(pageable);

        assertEquals(2, resultPage.getContent().size());
        verify(addressRepository).findAll(pageable);
    }
}

