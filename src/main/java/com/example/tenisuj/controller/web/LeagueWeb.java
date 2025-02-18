//package com.example.tenisuj.controller.web;
//
//import com.example.tenisuj.model.League;
//import com.example.tenisuj.model.dto.UpdateLeagueDto;
//import com.example.tenisuj.service.LeagueService;
//import com.example.tenisuj.service.PlayerService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/leagues")
//@Slf4j
//public class LeagueWeb {
//
//    private final LeagueService leagueService;
//    private final PlayerService playerService;
//
//    @Autowired
//    public LeagueWeb(LeagueService leagueService, PlayerService playerService) {
//        this.leagueService = leagueService;
//        this.playerService = playerService;
//    }
//
//    @GetMapping("/")
//    public String getAllLeagues(Model model) {
//        setDefaultValues(model);
//        model.addAttribute("leagues", leagueService.getAllLeagues());
//        model.addAttribute("league", new League());
//        return "leagues";
//    }
//
//
//    @PostMapping("/create")
//    public String createLeague(@ModelAttribute("league") League league, Model model) {
//        setDefaultValues(model);
//        leagueService.addLeague(league.getId(), league.getName());
//        return "redirect:/leagues/";
//    }
//
//    @GetMapping("/add-matches/{id}")
//    public String addMatches(@PathVariable("id") String leagueId, Model model) {
//        setDefaultValues(model);
//        leagueService.leagueMatchGenerator(leagueId);
//        return "redirect:/leagues/details/" + leagueId;
//    }
//
//    @GetMapping("/details/{id}")
//    public String getLeague(@PathVariable("id") String leagueId, UpdateLeagueDto updateLeagueDto, Model model) {
//        setDefaultValues(model);
//        model.addAttribute("league", leagueService.getLeague(leagueId));
//        model.addAttribute("players", playerService.getAllPlayers(null));
//        model.addAttribute("updateLeagueDto", updateLeagueDto);
//        return "leagueDetails";
//    }
//
//    @PostMapping("/details/{id}/add")
//    public String addPlayerToLeague(@PathVariable("id") String leagueId, UpdateLeagueDto updateLeagueDto, Model model) {
//        model.addAttribute("player", playerService.getPlayerById(updateLeagueDto.getPlayerId()));
//        leagueService.addPlayerToLeague(leagueId, updateLeagueDto.getPlayerId());
//        log.info("add player to league {}", leagueId);
//        return "redirect:/leagues/details/" + leagueId;
//    }
//
//    private void setDefaultValues(Model model) {
//        model.addAttribute("pageTitle", "Leagues");
//    }
//}