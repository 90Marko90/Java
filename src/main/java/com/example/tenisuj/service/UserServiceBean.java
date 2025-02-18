package com.example.tenisuj.service;
import com.example.tenisuj.Exception.CustomHttpException;
import com.example.tenisuj.Mapper.UserMapper;
import com.example.tenisuj.model.User;
import com.example.tenisuj.model.dto.CredentialsDto;
import com.example.tenisuj.model.dto.SignUpDto;
import com.example.tenisuj.model.dto.UserDto;
import com.example.tenisuj.model.enums.Role;
import com.example.tenisuj.repository.PlayerRepository;
import com.example.tenisuj.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceBean implements UserService {

    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceBean(UserRepository userRepository, PlayerRepository playerRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public void addUser(String username, String password) {
        if (!StringUtils.hasText(username)) {
            throw new IllegalArgumentException("Username is empty");
        }
        if (!StringUtils.hasText(password)) {
            throw new IllegalArgumentException("Password is empty");
        }
        Role role = Role.USER;
        String roleString = role.getRole(); // Assuming this returns a String
        Role userRole = Role.valueOf(roleString);

        var User = new User(username, passwordEncoder.encode(password), userRole);

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
    public void updateUser(String username, String password, String playerId) {
        var user = userRepository
                .findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var updated = new User();

        updated.setUsername(username);

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

    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByUsername(credentialsDto.username())
                .orElseThrow(() -> new CustomHttpException("Unknown user", HttpStatus.NOT_FOUND));
        //compare the given password with the password in DB
        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
            //if correct, then maps the user from DB to UserDto
            return userMapper.toUserDto(user);
        }
        throw new CustomHttpException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto) {
        Optional<User> optionalUser = userRepository.findByUsername(userDto.username());
        if (optionalUser.isPresent()) {
            throw new CustomHttpException("Login already exists", HttpStatus.BAD_REQUEST);
        }
        User user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));
        user.setRole(Role.USER);
        User savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser);
    }

    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomHttpException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }
}
