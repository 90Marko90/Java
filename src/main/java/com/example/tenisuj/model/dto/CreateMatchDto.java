package com.example.tenisuj.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateMatchDto {
    private String player1Id;
    private String player2Id;
}
