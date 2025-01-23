package com.example.tenisuj.service;

import com.example.tenisuj.model.User;
import com.example.tenisuj.model.enums.Role;
import com.example.tenisuj.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceBean implements UserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String USER_ROLE = "USER";

    @Autowired
    public UserServiceBean(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User addUser(String username, String password) {
        return null;
    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public User getUser(String username) {
        return userRepository
                .findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User updateUser(String username, String password) {
        return null;
    }

    @Override
    public List<User> getUsersByName(String name) {
        return List.of();
    }

    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
