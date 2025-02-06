//package com.example.tenisuj.service;
//import com.example.tenisuj.Exception.CustomHttpException;
//import com.example.tenisuj.Mapper.UserMapper;
//import com.example.tenisuj.model.User;
//import com.example.tenisuj.model.dto.CredentialsDto;
//import com.example.tenisuj.model.dto.SignUpDto;
//import com.example.tenisuj.model.dto.UserDto;
//import com.example.tenisuj.repository.UserRepository2;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import java.nio.CharBuffer;
//import java.util.Optional;
////created
//@RequiredArgsConstructor
//@Service
//public class UserService2 {
//
//    private final UserRepository2 userRepository2;
//
//    private final PasswordEncoder passwordEncoder;
//
//    private final UserMapper userMapper;
//
//    public UserDto login(CredentialsDto credentialsDto) {
//        User user = userRepository2.findByLogin(credentialsDto.login())
//                .orElseThrow(() -> new CustomHttpException("Unknown user", HttpStatus.NOT_FOUND));
//
//        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
//            return userMapper.toUserDto(user);
//        }
//        throw new CustomHttpException("Invalid password", HttpStatus.BAD_REQUEST);
//    }
//
//    public UserDto register(SignUpDto userDto) {
//        Optional<User> optionalUser = userRepository2.findByLogin(userDto.login());
//
//        if (optionalUser.isPresent()) {
//            throw new CustomHttpException("Login already exists", HttpStatus.BAD_REQUEST);
//        }
//
//        User user = userMapper.signUpToUser(userDto);
//        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));
//
//        User savedUser = userRepository2.save(user);
//
//        return userMapper.toUserDto(savedUser);
//    }
//
//    public UserDto findByLogin(String login) {
//        User user = userRepository2.findByLogin(login)
//                .orElseThrow(() -> new CustomHttpException("Unknown user", HttpStatus.NOT_FOUND));
//        return userMapper.toUserDto(user);
//    }
//
//}