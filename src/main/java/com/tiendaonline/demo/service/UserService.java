package com.tiendaonline.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.tiendaonline.demo.model.User;
import com.tiendaonline.demo.model.Role;

@Service
public class UserService {
    private final InMemoryDatabase inMemoryDatabase;

    // Inyectamos la dependencia de InMemoryDatabase
    public UserService(InMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }

    // Método que devuelve la lista de usuarios
    public List<User> getUsers() {
        return inMemoryDatabase.getUsers();
    }

    // Método que devuelve un usuario por su id
    public Optional<User> getUserById(Integer id) {
        return inMemoryDatabase.getUserById(id);
    }

    // Método para filtrar usuarios por su rol
    public List<User> getUsersByRole(Role role) {
        return inMemoryDatabase.getUsers().stream()
                .filter(user -> user.getRole().equals(role))
                .collect(Collectors.toList());
    }
}