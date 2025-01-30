package com.example.tenisuj.service;

import com.example.tenisuj.model.Match;

import java.util.List;

public interface MatchService {
    Match addMatch(String player1Id, String player2Id);
    Match getMatch(String matchId);
    List<Match> getMatches();
    void deleteMatch(String matchId);
    Match addResult(
            String matchId,
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
            String scratchedPlayerId,
            String winnerPlayerId);
}
