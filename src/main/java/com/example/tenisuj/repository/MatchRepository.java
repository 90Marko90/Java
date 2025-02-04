package com.example.tenisuj.repository;

import com.example.tenisuj.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MatchRepository extends JpaRepository<Match, String> {

    @Query("select m from Match m where m.player1.id= :playerId or m.player2.id= :playerId")
    List<Match> findAllPlayerMatches(String playerId);

    @Query("select m from Match m where m.winner.id= :playerId")
    List<Match> findWonPlayerMatches(String playerId);
}
