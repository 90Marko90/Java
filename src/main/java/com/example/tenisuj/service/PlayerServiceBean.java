package com.example.tenisuj.service;

import com.example.tenisuj.model.Player;
import com.example.tenisuj.repository.MatchRepository;
import com.example.tenisuj.repository.PlayerRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PlayerServiceBean implements PlayerService {

    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;

    @Autowired
    public PlayerServiceBean(PlayerRepository playerRepository, MatchRepository matchRepository) {
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public Player addPlayer(@NonNull String firstName, @NonNull String lastName, String email, String gender, LocalDate birthday, Boolean leagueStatus, String hand, int rating, LocalDate registrationDate) {

        registrationDate = LocalDate.now();

        if (gender.equalsIgnoreCase("M")) {
            gender = "Male";
        } else if (gender.equalsIgnoreCase("F")) {
            gender = "Female";
        } else {
            gender = "Unknown";
        }
        if (hand.equalsIgnoreCase("L")) {
            hand = "Left";
        } else if (hand.equalsIgnoreCase("R")) {
            hand = "Right";
        } else {
            hand = "Unknown";
        }

        var player = new Player(UUID.randomUUID().toString(), firstName, lastName, email, gender, birthday, leagueStatus, hand, rating, registrationDate);
        log.info("Adding player: {}", player);
        return playerRepository.save(player);
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll().stream().toList();
    }

    @Override
    public Player getPlayerById(@NonNull String id) {

        return playerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));
    }

    @Override
    public void deletePlayer(@NonNull String id) {
        if (!playerRepository.existsById(id)) {
            throw new IllegalArgumentException("Player not found");
        }
        playerRepository.deleteById(id);
        log.info("Deleted player: {}", playerRepository.findById(id));

    }

    @Override
    public int updateRating(String playerId) {
        var player = playerRepository.findById(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));
        player.setRating(100/matchRepository.findAllPlayedPlayerMatches(playerId).size()*matchRepository.findWonPlayerMatches(playerId).size());
        playerRepository.save(player);
        log.info("Updated player rating: {}", player);
        return player.getRating();
    }

}
