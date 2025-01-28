package com.example.tenisuj.service;

import com.example.tenisuj.model.User;

import java.util.List;

public interface UserService {
    void addUser(String username, String password);

    void deleteUser(String username);

    User getUser(String username);

    List<User> getAllUsers();

    void updateUser(String username, String password, String playerId);

    List<User> getUsersByName(String name);

}
