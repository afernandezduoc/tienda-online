package com.tiendaonline.demo.service;

import com.tiendaonline.demo.model.Address;
import com.tiendaonline.demo.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {
    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address(1, "123 Main Street", "Sometown", "Somecountry");
    }

    // Verifica que el servicio devuelva el Address correcto cuando se busca por ID.
    @Test
    void testFindAddressById() {
        when(addressRepository.findById(1)).thenReturn(Optional.of(address));
        
        Optional<Address> foundAddress = addressService.findAddressById(1);
        
        verify(addressRepository).findById(1);
        assert foundAddress.isPresent();
        assertEquals(address.getId(), foundAddress.get().getId());
    }

    // Confirma que el servicio puede guardar un Address y devuelve el objeto correcto.
    @Test
    void testSaveAddress() {
        when(addressRepository.save(any(Address.class))).thenReturn(address);
        
        Address savedAddress = addressService.saveAddress(address);
        
        verify(addressRepository).save(address);
        assertEquals(address.getId(), savedAddress.getId());
    }

    // Verifica que el servicio elimine el Address correcto cuando se busca por ID.
    @Test
    void testDeleteAddress() {
        doNothing().when(addressRepository).deleteById(address.getId());
        
        addressService.deleteAddress(address.getId());
        
        verify(addressRepository).deleteById(address.getId());
    }
}
