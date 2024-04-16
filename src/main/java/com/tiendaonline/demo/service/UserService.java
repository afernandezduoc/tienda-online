package com.tiendaonline.demo.service;

import com.tiendaonline.demo.model.User;
import com.tiendaonline.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Obtener todos los usuarios
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // Encontrar un usuario por ID
    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(id);
    }

    // Guardar o actualizar un usuario
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Eliminar un usuario
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
    
    // Ejemplo de método para actualizar un usuario
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
