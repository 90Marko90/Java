package com.example.tenisuj.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateProfileDto {
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private LocalDate birthDate;
    private String leagueStatus;
    private String hand;
    private Integer rating;
}
