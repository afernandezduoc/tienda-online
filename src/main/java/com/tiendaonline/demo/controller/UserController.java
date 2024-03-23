package com.tiendaonline.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiendaonline.demo.model.User;
import com.tiendaonline.demo.model.Role;
import com.tiendaonline.demo.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    // Inyectamos la dependencia de UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint que devuelve la lista de usuarios
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    // Endpoint que devuelve un usuario por su id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint que devuelve la lista de usuarios por su rol
    @GetMapping("/role/{role}")
    public ResponseEntity<?> getUsersByRole(@PathVariable("role") String roleString) {
        Role role;
        try {
            role = Role.valueOf(roleString.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El rol no es válido o no existe.");
        }

        List<User> users = userService.getUsersByRole(role);
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron usuarios con el rol: " + roleString);
        }
        return ResponseEntity.ok(users);
    }
}
