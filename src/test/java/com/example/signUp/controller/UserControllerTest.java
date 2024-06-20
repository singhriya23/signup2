package com.example.signUp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.signUp.model.User;
import com.example.signUp.service.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Collections;

import static org.hamcrest.Matchers.is;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testSignupUser() throws Exception {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setAddress("123 Main St");
        user.setContact("1234567890");

        when(userService.saveUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"address\":\"123 Main St\",\"contact\":\"1234567890\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John Doe")))
                .andExpect(jsonPath("$.email", is("john.doe@example.com")))
                .andExpect(jsonPath("$.address", is("123 Main St")))
                .andExpect(jsonPath("$.contact", is("1234567890")));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setAddress("123 Main St");
        user.setContact("1234567890");

        when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("John Doe")))
                .andExpect(jsonPath("$[0].email", is("john.doe@example.com")))
                .andExpect(jsonPath("$[0].address", is("123 Main St")))
                .andExpect(jsonPath("$[0].contact", is("1234567890")));
    }
}