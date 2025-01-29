package com.example.tenisuj;

import com.example.tenisuj.model.League;
import com.example.tenisuj.model.Match;
import com.example.tenisuj.model.Player;
import com.example.tenisuj.model.User;
import com.example.tenisuj.repository.LeagueRepository;
import com.example.tenisuj.repository.MatchRepository;
import com.example.tenisuj.repository.PlayerRepository;
import com.example.tenisuj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@SpringBootApplication
public class TenisujApplication implements CommandLineRunner {


    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;
    private final LeagueRepository leagueRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public TenisujApplication(UserRepository userRepository, PlayerRepository playerRepository, MatchRepository matchRepository, LeagueRepository leagueRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
        this.leagueRepository = leagueRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public static void main(String[] args) {
        SpringApplication.run(TenisujApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        List<User> users = new ArrayList<>(List.of(
                new User("Marek", "ROLE_USER", passwordEncoder.encode("123"))
        ));

        List<Player> players = new ArrayList<>(List.of(
                new Player(UUID.randomUUID().toString(), "meno", "priezvisko", "email", "gender", LocalDate.now(), true, "L", 10, LocalDate.now()),
                new Player(UUID.randomUUID().toString(), "meno", "priezvisko", "email", "gender", LocalDate.now(), true, "L", 10, LocalDate.now())
        ));

        Match match = new Match(UUID.randomUUID().toString(),players.get(0),players.get(1),7,5,6,4,null,null,null,null,null,null,null);
        Match match2 = new Match(UUID.randomUUID().toString(),players.get(0),players.get(1),7,5,6,4,null,null,null,null,null,null,null);

        League league = new League(UUID.randomUUID().toString(),"League",null);

        users.getFirst().setPlayer(players.get(1));
        league.setPlayers(players);

        playerRepository.saveAll(players);

        for (User user : users) {
            userRepository.save(user);
        }

        matchRepository.save(match);
        matchRepository.save(match2);

        leagueRepository.save(league);


    }


}
