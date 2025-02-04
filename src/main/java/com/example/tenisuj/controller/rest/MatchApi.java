package com.example.tenisuj.controller.rest;

import com.example.tenisuj.model.Match;
import com.example.tenisuj.model.dto.CreateMatchDto;
import com.example.tenisuj.model.dto.UpdateMatchLocationDateAndTimeDto;
import com.example.tenisuj.model.dto.UpdateResultDto;
import com.example.tenisuj.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rest/matches")
public class MatchApi {
    private final MatchService matchService;

    @Autowired
    public MatchApi(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/")
    List<Match> getMatches() {
        log.info("getMatches");
        return matchService.getMatches();
    }
    @GetMapping("/player_matches/{playerId}")
    List<Match> getPlayerMatches(@PathVariable("playerId") String playerId) {
        log.info("getPlayerMatches");
        return matchService.findAllPlayerMatches(playerId);
    }

    @GetMapping("/player_win/{playerId}")
    List<Match> getWonPlayerMatches(@PathVariable("playerId") String playerId) {
        log.info("getWonPlayerMatches");
        return matchService.findWonPlayerMatches(playerId);
    }

    @PostMapping("/create")
    ResponseEntity<Match> createMatch(@RequestBody CreateMatchDto match) {
        Match created = matchService.addMatch(match.getPlayer1Id(), match.getPlayer2Id());
        log.info("createMatch");
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Match> deleteMatch(@PathVariable("id") String id) {
        matchService.deleteMatch(id);
        log.info("deleteMatch");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PatchMapping("/{id}/add_location_date_and_time")
    ResponseEntity<Match> updateMatchLocationDateAndTime(@PathVariable("id") String id, @RequestBody UpdateMatchLocationDateAndTimeDto locationDateAndTime) {
        Match matchLocationDateAndTime = matchService.addLocation(id, locationDateAndTime.getLocation(), locationDateAndTime.getDateTime());
        log.info("updateMatchLocationDateAndTime");
        return new ResponseEntity<>(matchLocationDateAndTime, HttpStatus.OK);
    }
    @PatchMapping("/{id}/add_result")
    ResponseEntity<Match> updateMatchResult(@PathVariable("id") String id, @RequestBody UpdateResultDto result) {
        Match matchResult = matchService.addResult(id, result.getPlayer1_set1(), result.getPlayer2_set1(), result.getPlayer1_set2(), result.getPlayer2_set2(), result.getPlayer1_set3(), result.getPlayer2_set3(), result.getPlayer1_set4(), result.getPlayer2_set4(), result.getPlayer1_set5(), result.getPlayer2_set5(), result.getScratchedPlayerId(), result.getWinnerPlayerId());
        log.info("updateMatchResult");
        return new ResponseEntity<>(matchResult, HttpStatus.OK);
    }
}
