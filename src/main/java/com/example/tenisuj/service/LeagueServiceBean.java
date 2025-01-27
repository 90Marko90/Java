package com.example.tenisuj.service;

import com.example.tenisuj.model.League;
import com.example.tenisuj.repository.LeagueRepository;
import com.example.tenisuj.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class LeagueServiceBean implements LeagueService {

    private final LeagueRepository leagueRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public LeagueServiceBean(LeagueRepository leagueRepository, PlayerRepository playerRepository) {
        this.leagueRepository = leagueRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public void addLeague(String leagueId, String leagueName) {
        if (leagueRepository.existsByName(leagueName)) {
            throw new IllegalArgumentException("League already exists!");
        }
        League league = new League(UUID.randomUUID().toString(), leagueName, null);
        log.info("Adding league {}", leagueName);
        leagueRepository.save(league);

    }

    @Override
    public List<League> getAllLeagues() {
        return leagueRepository.findAll().stream().toList();
    }

    @Override
    public League getLeague(String leagueId) {
        return leagueRepository
                .findById(leagueId)
                .orElseThrow(() -> new IllegalArgumentException("League not found"));
    }

    @Override
    public void deleteLeague(String leagueId) {
        if (!leagueRepository.existsById(leagueId)) {
            throw new IllegalArgumentException("League does not exists!");
        }
        leagueRepository.deleteById(leagueId);
        log.info("Deleted league {}", leagueId);
    }

    @Override
    public League addPlayerToLeague(String leagueId, String playerId) {
        var league = leagueRepository.findById(leagueId)
                .orElseThrow(() -> new IllegalArgumentException("League not found"));
        var player = playerRepository.findById(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));
        league.getPlayers().add(player);
        log.info("Adding player to league {}", league);
        leagueRepository.save(league);
        return league;
    }
}
