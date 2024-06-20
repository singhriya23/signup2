package com.example.signUp.service;

import java.util.List;

import com.example.signUp.model.User;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    void deleteUserById(Long id);
    
}
