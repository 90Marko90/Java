package com.example.tenisuj.service;

import com.example.tenisuj.model.User;

import java.util.List;

public interface UserService {
    User addUser(String username, String password);
    void deleteUser(String username);
    User getUser(String username);
    List<User> getAllUsers();
    User updateUser(String username, String password);
    List<User> getUsersByName(String name);
}
