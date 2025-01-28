package com.example.tenisuj.service;

import com.example.tenisuj.model.League;
import com.example.tenisuj.model.Match;
import com.example.tenisuj.model.Player;
import com.example.tenisuj.model.User;
import com.example.tenisuj.repository.MatchRepository;
import com.example.tenisuj.repository.PlayerRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

        Match match = new Match(UUID.randomUUID().toString(),player1,player2,null,null,null,null,null,null,null,null,null,null,null);

        matchRepository.save(match);
        log.info("Match added");
        return match;
    }

    @Override
    public Match getMatch(String matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found"));
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

    @Override
    public void addResult(String matchId,
                           Integer player1_set1,
                           Integer player2_set1,
                           Integer player1_set2,
                           Integer player2_set2,
                           Integer player1_set3,
                           Integer player2_set3,
                           Integer player1_set4,
                           Integer player2_set4,
                           Integer player1_set5,
                           Integer player2_set5,
                           String playerId) {

        var match = matchRepository
                .findById(matchId)
                .orElseThrow(() -> new UsernameNotFoundException("Match not found"));

        Player scratchnigPlayer = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));
        // need to add validation if scratchingPlayer is Player1 or Player2

        Match updated = new Match(matchId,matchRepository.findById(matchId).get().getPlayer1(),matchRepository.findById(matchId).get().getPlayer1(),player1_set1,player2_set1,player1_set2, player2_set2, player1_set3, player2_set3, player1_set4, player2_set4, player1_set5, player2_set5, scratchnigPlayer);

        matchRepository.save(updated);
        log.info("Match result updated: {}", updated.getId());

    }
}
