package com.tiendaonline.demo.controller;

import static org.mockito.Mockito.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.tiendaonline.demo.model.Address;
import com.tiendaonline.demo.model.Role;
import com.tiendaonline.demo.model.User;
import com.tiendaonline.demo.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetAllUsers() throws Exception {
        User user = new User(1, "Test User", "test@example.com", Role.USER, new Address());
        List<User> allUsers = Arrays.asList(user);

        when(userService.findAllUsers()).thenReturn(allUsers);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(user.getId())));
                //.andExpect(jsonPath("$[0]._links.self.href", endsWith("/users/" + user.getId())))
                //.andExpect(jsonPath("$[0]._links.allUsers.href", endsWith("/users")));
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = new User(1, "Test User", "test@example.com", Role.USER, new Address());
        given(userService.findUserById(1)).willReturn(Optional.of(user));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$._links.self.href", endsWith("/users/" + user.getId())))
                //.andExpect(jsonPath("$._links.allUsers.href", endsWith("/users")));
    }
}
