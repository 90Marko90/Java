package com.example.tenisuj.controller.web;

import com.example.tenisuj.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeWeb {
    private final HomeService homeService;

    @Autowired
    public HomeWeb(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        setDefaultValues(model);
        model.addAttribute("home", homeService.getHome());
        return "home";
    }

    private void setDefaultValues(Model model) {
        model.addAttribute("pageTitle", "Tenisuj-sk");
    }
}
