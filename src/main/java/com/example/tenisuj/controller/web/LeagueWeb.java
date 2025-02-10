package com.example.tenisuj.controller.web;

import com.example.tenisuj.model.League;
import com.example.tenisuj.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/leagues")
public class LeagueWeb {

    private final LeagueService leagueService;

    @Autowired
    public LeagueWeb(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping("/")
    public String getAllLeagues(Model model) {
        setDefaultValues(model);
        model.addAttribute("leagues", leagueService.getAllLeagues());
        model.addAttribute("league", new League());
        return "leagues";
    }

    @PostMapping("/create")
    public String createLeague(@ModelAttribute("league") League league, Model model) {
        setDefaultValues(model);
        leagueService.addLeague(league.getId(), league.getName());
        return "redirect:/leagues/";
    }

    private void setDefaultValues(Model model) {
        model.addAttribute("pageTitle", "Leagues");
    }
}
