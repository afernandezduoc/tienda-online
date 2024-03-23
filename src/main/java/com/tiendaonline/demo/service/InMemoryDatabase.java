package com.tiendaonline.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tiendaonline.demo.model.Address;
import com.tiendaonline.demo.model.Role;
import com.tiendaonline.demo.model.User;

import jakarta.annotation.PostConstruct;

@Service
public class InMemoryDatabase {
    private List<User> users;
    private List<Address> addresses;

    // Los métodos @PostConstruct init() inicializan las listas con datos de prueba al arrancar la aplicación.
    @PostConstruct
    public void init() {
        // Inicializa las listas de usuarios y direcciones
        users = new ArrayList<>();
        addresses = new ArrayList<>();

        // Crea y añade direcciones de prueba
        addresses.add(new Address(1, "Calle Falsa 123", "Ciudad Gótica", "País Imaginario"));
        addresses.add(new Address(2, "Avenida Siempre Viva 742", "Springfield", "USA"));
        addresses.add(new Address(3, "Plaza Sesamo 456", "Barrio Sésamo", "Fantasía"));

        // Crea y añade usuarios de prueba
        users.add(new User(1, "Juan Pérez", "juan@example.com", Role.USER, addresses.get(0)));
        users.add(new User(2, "Ana López", "ana@example.com", Role.ADMIN, addresses.get(1)));
        users.add(new User(3, "Carlos García", "carlos@example.com", Role.GUEST, addresses.get(2)));
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Address> getAddresses() {
        return addresses;
    }
}
