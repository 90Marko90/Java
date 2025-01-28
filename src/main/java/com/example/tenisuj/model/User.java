package com.example.tenisuj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity (name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {

    @Id
    private String username;

    private String password;

    private String role;

}
