package com.tiendaonline.demo.service;

import com.tiendaonline.demo.model.Address;
import com.tiendaonline.demo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    // Obtener todas las direcciones
    public List<Address> findAllAddresses() {
        return addressRepository.findAll();
    }

    // Encontrar una dirección por ID
    public Optional<Address> findAddressById(Integer id) {
        return addressRepository.findById(id);
    }

    // Guardar o actualizar una dirección
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    // Eliminar una dirección
    public void deleteAddress(Integer id) {
        addressRepository.deleteById(id);
    }
    
    // Ejemplo de método para actualizar una dirección
    public Address updateAddress(Address address) {
        return addressRepository.save(address);
    }
}