package com.example.tenisuj.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateResultDto {
    private String matchId;
    private Integer player1_set1;
    private Integer player2_set1;
    private Integer player1_set2;
    private Integer player2_set2;
    private Integer player1_set3;
    private Integer player2_set3;
    private Integer player1_set4;
    private Integer player2_set4;
    private Integer player1_set5;
    private Integer player2_set5;
    private String scratchedPlayerId;
    private String winnerPlayerId;

}
