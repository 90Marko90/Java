package com.example.tenisuj.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {

    @Id
    @Column(name="username")
    @Size(max = 30)
    private String username;

    @Column(name="password")
    @Size(max = 30)
    private String password;

    @Column(name="role")
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
