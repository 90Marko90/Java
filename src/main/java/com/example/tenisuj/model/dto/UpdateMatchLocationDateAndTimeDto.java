package com.example.tenisuj.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateMatchLocationDateAndTimeDto {
    private String matchId;
    private String location;
    private LocalDateTime dateTime;
}
