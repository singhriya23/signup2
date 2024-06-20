package com.example.signUp.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.signUp.model.User;
import com.example.signUp.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;

import java.util.Collections;
import java.util.List;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setAddress("123 Main St");
        user.setContact("1234567890");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertEquals("John Doe", savedUser.getName());
        assertEquals("john.doe@example.com", savedUser.getEmail());
        assertEquals("123 Main St", savedUser.getAddress());
        assertEquals("1234567890", savedUser.getContact());
    }

    @Test
    public void testGetAllUsers() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setAddress("123 Main St");
        user.setContact("1234567890");

        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<User> users = userService.getAllUsers();

        assertEquals(1, users.size());
        assertEquals("John Doe", users.get(0).getName());
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setAddress("123 Main St");
        user.setContact("1234567890");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1L);

        assertEquals("John Doe", foundUser.getName());
    }

    @Test
    public void testDeleteUserById() {
        userService.deleteUserById(1L);
    }
}
