package com.example.tenisuj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;

    public User(String username, String role, String password) {
        this.username = username;
        this.role = role;
        this.password = password;
    }
}
