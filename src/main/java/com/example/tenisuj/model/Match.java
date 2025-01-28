package com.example.tenisuj.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "matches")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Match {

    @Id
    private String id;

    @ManyToOne
    private Player player1;

    @ManyToOne
    private Player player2;

    private String result;

}
