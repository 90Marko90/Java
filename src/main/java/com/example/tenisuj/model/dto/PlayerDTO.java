package com.example.tenisuj.model.dto;

import com.example.tenisuj.model.Player;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@Getter
public class PlayerDTO {

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
    private int age;

    public PlayerDTO(Player player) {
        this.id = player.getId();
        this.firstName = player.getFirstName();
        this.lastName = player.getLastName();
        this.email = player.getEmail();
        this.gender = player.getGender();
        this.birthDate = player.getBirthDate();
        this.leagueStatus = player.getLeagueStatus();
        this.hand = player.getHand();
        this.rating = player.getRating();
        this.registrationDate = player.getRegistrationDate();
        this.age = calculateAge(player.getBirthDate());
    }

    private int calculateAge(LocalDate birthDate) {
        if (birthDate != null) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }
}

