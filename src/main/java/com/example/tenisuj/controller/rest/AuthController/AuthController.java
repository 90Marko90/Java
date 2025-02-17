package com.example.tenisuj.controller.rest.AuthController;
import com.example.tenisuj.model.User;
import com.example.tenisuj.model.dto.CredentialsDto;
import com.example.tenisuj.model.dto.SignUpDto;
import com.example.tenisuj.model.dto.UserDto;
import com.example.tenisuj.security.UserAuthenticationProvider;
import com.example.tenisuj.service.UserServiceBean;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;

@CrossOrigin(origins="http://localhost:4200")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserServiceBean userServiceBean;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
        UserDto userDto = userServiceBean.login(credentialsDto);
        userDto.setToken(userAuthenticationProvider.createToken(userDto));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid SignUpDto user) {
        UserDto createdUser = userServiceBean.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(createdUser));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getUsername())).body(createdUser);
    }

}