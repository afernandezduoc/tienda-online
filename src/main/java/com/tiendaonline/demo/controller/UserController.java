package com.tiendaonline.demo.controller;

import com.tiendaonline.demo.model.User;
import com.tiendaonline.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /* 
    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }*/

    // Obtener todos los usuarios con HATEOAS
    @GetMapping
    public ResponseEntity<List<EntityModel<User>>> getAllUsers() {
        List<EntityModel<User>> users = userService.findAllUsers().stream()
            .map(user -> EntityModel.of(user,
                linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("allUsers")))
            .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    /* 
    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> user = userService.findUserById(id);
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    } */

    // Obtener un usuario por ID con HATEOAS
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<User>> getUserById(@PathVariable Integer id) {
        Optional<User> user = userService.findUserById(id);
        return user.map(u -> EntityModel.of(u,
                linkTo(methodOn(UserController.class).getUserById(u.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("allUsers")))
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        return userService.findUserById(id)
                          .map(existingUser -> {
                              user.setId(id);
                              User updatedUser = userService.saveUser(user);
                              return new ResponseEntity<>(updatedUser, HttpStatus.OK);
                          })
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        return userService.findUserById(id)
                          .map(user -> {
                              userService.deleteUser(id);
                              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                          })
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
