package com.example.tenisuj.service;

import com.example.tenisuj.model.Match;

import java.util.List;

public interface MatchService {
    Match addMatch(String player1Id, String player2Id);
    List<Match> getMatches();
    void deleteMatch(String matchId);
}
