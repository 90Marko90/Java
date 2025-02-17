package com.example.tenisuj.service;
import com.example.tenisuj.model.Player;
import lombok.NonNull;
import java.time.LocalDate;
import java.util.List;

public interface PlayerService {
    Player addPlayer(String firstName,
                     String lastName,
                     String email,
                     String gender,
                     LocalDate birthday,
                     Boolean leagueStatus,
                     String hand,
                     int rating,
                     LocalDate registrationDate);

    List<Player> getAllPlayers();

    Player getPlayerById(@NonNull String id);

    void deletePlayer(@NonNull String id);

    int updateRating(String playerId);

}
