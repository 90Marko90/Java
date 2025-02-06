//package com.example.tenisuj.security;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.stereotype.Component;
////created
//@Component
//public class PasswordConfig {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    //created
//    @RequiredArgsConstructor
//    @Configuration
//    @EnableWebSecurity
//    public static class SecurityConfig {
//
//        private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
//        private final UserAuthenticationProvider userAuthenticationProvider;
//
//        @Bean
//        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//            http
//                    .exceptionHandling(customizer -> customizer.authenticationEntryPoint(userAuthenticationEntryPoint))
//                    .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
//                    .csrf(AbstractHttpConfigurer::disable)
//                    .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                    .authorizeHttpRequests((requests) -> requests
//                            .requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()
//                            .anyRequest().authenticated())
//            ;
//            return http.build();
//        }
//    }
//}