package com.example.tenisuj.service;

import com.example.tenisuj.model.User;
import com.example.tenisuj.model.enums.Role;
import com.example.tenisuj.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class UserServiceBean implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceBean(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(String username, String password) {
        if (!StringUtils.hasText(username)) {
            throw new IllegalArgumentException("Username is empty");
        }
        if (!StringUtils.hasText(password)) {
            throw new IllegalArgumentException("Password is empty");
        }
        var User = new User(username, encryptPassword(password), Role.USER.getRole());

        if (userRepository.existsById(username)) {
            throw new IllegalArgumentException("User already exists");
        }
        userRepository.save(User);
        log.info("User created: {}", username);
    }

    @Override
    public void deleteUser(String username) {
        if (!userRepository.existsById(username)) {
            throw new IllegalArgumentException("User does not exist");
        }
        userRepository.deleteById(username);
        log.info("User deleted: {}", username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll().stream().toList();
    }

    @Override
    public User getUser(String username) {
        return userRepository
                .findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public void updateUser(String username, String password) {
        var user = userRepository
                .findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var updated = new User();

        updated.setUsername(username);

        if (StringUtils.hasText(username)) {
            updated.setUsername(username);
        } else {
            updated.setUsername(user.getUsername());
        }
        if (StringUtils.hasText(password)) {
            updated.setPassword(encryptPassword(password));
        } else {
            updated.setPassword(user.getPassword());
        }
        updated.setRole(user.getRole());
        userRepository.save(updated);
        log.info("User updated: {}", updated.getUsername());
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userRepository.findByUsernameContainingIgnoreCase(name);
    }

    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
