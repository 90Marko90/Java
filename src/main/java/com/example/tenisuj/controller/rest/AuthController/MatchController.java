package com.example.tenisuj.controller.rest.AuthController;
import com.example.tenisuj.model.Match;
import com.example.tenisuj.repository.MatchRepository;
import com.example.tenisuj.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;
    private MatchRepository matchRepository;

    public MatchController(MatchService matchService, MatchRepository matchRepository) {
        this.matchService = matchService;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/getMatches")
    public List<Match> getMatches() {
        return matchRepository.findAll().stream().toList();
    }

}