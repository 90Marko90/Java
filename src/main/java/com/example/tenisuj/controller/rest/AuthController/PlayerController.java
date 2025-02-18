package com.example.tenisuj.controller.rest.AuthController;
import com.example.tenisuj.model.Player;
import com.example.tenisuj.repository.PlayerRepository;
import com.example.tenisuj.service.PlayerService;
import com.example.tenisuj.service.PlayerServiceBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/players")
public class PlayerController {

    private PlayerRepository playerRepository;
    private PlayerServiceBean playerServiceBean;

    @Autowired
    public PlayerController(PlayerRepository playerRepository, PlayerServiceBean playerServiceBean) {
        this.playerRepository = playerRepository;
        this.playerServiceBean = playerServiceBean;
    }

    @GetMapping("/getAllPlayers")
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @PostMapping("/create")
    ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        Player created = playerServiceBean.addPlayer(player.getFirstName(), player.getLastName(), player.getEmail(), player.getGender(), player.getBirthDate(), player.getLeagueStatus(), player.getHand(), player.getRating(), player.getRegistrationDate());
        log.info("Created player: {}", created);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    //    @PostMapping("/create")
    //    public Player addPlayer(@RequestBody Player player) {
    //        return playerRepository.save(player);
    //    }

}