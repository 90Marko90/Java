//package com.example.tenisuj.model;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
////created
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Data
//@Entity
//@Table(name = "app_user")
//public class User2 {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "first_name", nullable = false)
//    @Size(max = 100)
//    private String firstName;
//
//    @Column(name = "last_name", nullable = false)
//    @Size(max = 100)
//    private String lastName;
//
//    @Column(nullable = false)
//    @Size(max = 100)
//    private String login;
//
//    @Column(nullable = false)
//    @Size(max = 100)
//    private String password;
//}