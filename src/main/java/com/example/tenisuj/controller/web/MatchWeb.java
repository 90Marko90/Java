package com.example.tenisuj.controller.web;

import com.example.tenisuj.model.Match;
import com.example.tenisuj.model.Player;
import com.example.tenisuj.model.dto.PlayerDTO;
import com.example.tenisuj.service.MatchService;
import com.example.tenisuj.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/matches")
public class MatchWeb {

    private final MatchService matchService;
    private final PlayerService playerService;

    @Autowired
    public MatchWeb(MatchService matchService, PlayerService playerService) {
        this.matchService = matchService;
        this.playerService = playerService;
    }

    @GetMapping("/")
    String getAllMatches(Model model, @Param("playerName") String playerName) {
        setDefaultValues(model);
        model.addAttribute("matches", matchService.getMatches(playerName));
        model.addAttribute("player", playerService.getAllPlayers(null));
        model.addAttribute("playerName", playerName);
        return "matches";
    }

    private void setDefaultValues(Model model) {
        model.addAttribute("pageTitle", "Matches");
    }

}
