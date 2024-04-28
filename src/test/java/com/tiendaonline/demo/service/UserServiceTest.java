package com.tiendaonline.demo.service;

import com.tiendaonline.demo.model.Role;
import com.tiendaonline.demo.model.User;
import com.tiendaonline.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1, "Test User", "test@example.com", Role.USER, null);
    }

    @Test
    void shouldFindAllUsers() {
        // given
        given(userRepository.findAll()).willReturn(Arrays.asList(user));

        // when
        List<User> result = userService.findAllUsers();

        // then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(user.getName(), result.get(0).getName());
    }

    @Test
    void shouldFindUserById() {
        // given
        given(userRepository.findById(anyInt())).willReturn(Optional.of(user));

        // when
        Optional<User> result = userService.findUserById(1);

        // then
        assertTrue(result.isPresent());
        assertEquals(user.getName(), result.get().getName());
    }

    @Test
    void shouldSaveUser() {
        // given
        given(userRepository.save(any(User.class))).willReturn(user);

        // when
        User savedUser = userService.saveUser(user);

        // then
        assertNotNull(savedUser);
        assertEquals(user.getName(), savedUser.getName());
    }
}
