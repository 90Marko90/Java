package com.example.tenisuj.controller.rest;

import com.example.tenisuj.model.Match;
import com.example.tenisuj.model.dto.CreateMatchDto;
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
    @PatchMapping("/{id}")
    ResponseEntity<Match> updateMatchResult(@PathVariable("id") String id, @RequestBody UpdateResultDto result) {
        Match matchResult = matchService.addResult(result.getMatchId(), result.getPlayer1_set1(), result.getPlayer2_set1(), result.getPlayer1_set2(), result.getPlayer2_set2(), result.getPlayer1_set3(), result.getPlayer2_set3(), result.getPlayer1_set4(), result.getPlayer2_set4(), result.getPlayer1_set5(), result.getPlayer2_set5(), result.getScratchedPlayerId(), result.getWinnerPlayerId());
        log.info("updateMatchResult");
        return new ResponseEntity<>(matchResult, HttpStatus.OK);
    }
}
