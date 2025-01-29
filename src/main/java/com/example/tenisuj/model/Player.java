package com.example.tenisuj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity(name = "players")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Player {

    @Id
    private String id;

    private String firstName;
    private String lastName;

    private String email;

    private String gender;

    private LocalDate birthDate;

    private Boolean leagueStatus;

    private String hand;

    private int rating;

    private LocalDate registrationDate;

}
