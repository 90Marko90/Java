package com.example.tenisuj.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @Column(name="username")
    private String username;

    @Column(name="password")
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
