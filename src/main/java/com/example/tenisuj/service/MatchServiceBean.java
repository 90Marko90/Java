package com.example.tenisuj.service;

import com.example.tenisuj.model.Match;
import com.example.tenisuj.model.Player;
import com.example.tenisuj.repository.MatchRepository;
import com.example.tenisuj.repository.PlayerRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class MatchServiceBean implements MatchService {

    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public MatchServiceBean(MatchRepository matchRepository, PlayerRepository playerRepository) {

        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public Match addMatch(String player1Id,String player2Id) {

        Player player1 = playerRepository.findById(player1Id)
                .orElseThrow(() -> new RuntimeException("Player not found"));
        Player player2 = playerRepository.findById(player2Id)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        Match match = new Match(UUID.randomUUID().toString(),player1,player2,null);
//        Match match = new Match();
//        match.setId(UUID.randomUUID().toString());
//        match.setPlayer(player1);

        matchRepository.save(match);
        log.info("Match added");
        return match;
    }

    @Override
    public List<Match> getMatches() {
        return matchRepository.findAll().stream().toList();
    }

    @Override
    public void deleteMatch(@NonNull String matchId) {
        if (!matchRepository.existsById(matchId)) {
            throw new RuntimeException("Match not found");
        }
        matchRepository.deleteById(matchId);
        log.info("Match deleted");
    }
}
