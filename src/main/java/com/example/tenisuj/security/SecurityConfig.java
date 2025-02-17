package com.example.tenisuj.security;

import com.example.tenisuj.model.User;
import com.example.tenisuj.model.enums.Role;
import com.example.tenisuj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration

public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain restSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/rest/**")
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/rest/players/**").permitAll()
                                .requestMatchers("/rest/users/**").hasRole("ADMIN")
//                                .requestMatchers("/rest/users/**").hasRole("ROLE_USER")
                                .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            String username = "admin";
            if (!userRepository.existsById(username)) {
                User user = new User(username,Role.ADMIN.getRole(), passwordEncoder().encode("admin"));
                userRepository.save(user);
            }
        };
    }
}
