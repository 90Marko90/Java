//package com.example.tenisuj.security;
//
//import com.example.tenisuj.model.User;
//import com.example.tenisuj.model.enums.Role;
//import com.example.tenisuj.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//import java.util.Arrays;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
//    private final UserAuthenticationProvider userAuthenticationProvider;
//    private final UserDetailsService userDetailsService;
//    private final UserRepository userRepository;
//
//    public SecurityConfig(UserAuthenticationEntryPoint userAuthenticationEntryPoint, UserAuthenticationProvider userAuthenticationProvider, UserDetailsService userDetailsService, UserRepository userRepository) {
//        this.userAuthenticationEntryPoint = userAuthenticationEntryPoint;
//        this.userAuthenticationProvider = userAuthenticationProvider;
//        this.userDetailsService = userDetailsService;
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    protected void restSecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors().and().csrf().disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/login", "/register").permitAll()
//                .antMatchers("/rest/players/**").permitAll()
//                .antMatchers("/rest/users/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic()
//                .and()
//                .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
//                .and()
//                .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("http://localhost:4200");
//        config.setAllowedHeaders(Arrays.asList(
//                HttpHeaders.AUTHORIZATION,
//                HttpHeaders.CONTENT_TYPE,
//                HttpHeaders.ACCEPT));
//        config.setAllowedMethods(Arrays.asList(
//                HttpMethod.GET.name(),
//                HttpMethod.POST.name(),
//                HttpMethod.PUT.name(),
//                HttpMethod.DELETE.name()));
//        config.setMaxAge(10000L);
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public CommandLineRunner initDatabase() {
//        return args -> {
//            String username = "admin";
//            if (!userRepository.existsById(username)) {
//                User user = new User(username, Role.ADMIN.getRole(), passwordEncoder().encode("admin"));
//                userRepository.save(user);
//            }
//        };
//    }
//}