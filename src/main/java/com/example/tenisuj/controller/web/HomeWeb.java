package com.example.tenisuj.controller.web;

import com.example.tenisuj.model.Player;
import com.example.tenisuj.service.HomeService;
import com.example.tenisuj.service.PlayerService;
import com.example.tenisuj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeWeb {
    private final HomeService homeService;
    private final PlayerService playerService;
    private final UserService userService;

    @Autowired
    public HomeWeb(HomeService homeService, PlayerService playerService, UserService userService) {
        this.homeService = homeService;
        this.playerService = playerService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String player(Model model, Principal principal) {
        setDefaultValues(model);
        model.addAttribute("home", homeService.getHome());
        if (principal != null) {
            if (userService.getUser(principal.getName()).getPlayer() != null) {
                model.addAttribute("player", playerService.getPlayerById(userService.getUser(principal.getName()).getPlayer().getId()));
            } else model.addAttribute("player", null);
        } else model.addAttribute("player", null);
        return "home";
    }

    private void setDefaultValues(Model model) {
        model.addAttribute("pageTitle", "Tenisuj-sk");
    }
}
