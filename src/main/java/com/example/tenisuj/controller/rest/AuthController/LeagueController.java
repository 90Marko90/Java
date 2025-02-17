package com.example.tenisuj.controller.rest.AuthController;
import com.example.tenisuj.model.League;
import com.example.tenisuj.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/leagues")
public class LeagueController {

    @Autowired
    private LeagueRepository leagueRepository;

    public LeagueController(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    @GetMapping("/getAllLeagues")
    public List<League> getAllLeagues() {
        return leagueRepository.findAll().stream().toList();
    }

}
