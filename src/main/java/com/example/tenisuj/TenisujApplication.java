package com.example.tenisuj;

import com.example.tenisuj.model.League;
import com.example.tenisuj.model.Match;
import com.example.tenisuj.model.Player;
import com.example.tenisuj.model.User;
import com.example.tenisuj.model.enums.Role;
import com.example.tenisuj.repository.LeagueRepository;
import com.example.tenisuj.repository.MatchRepository;
import com.example.tenisuj.repository.PlayerRepository;
import com.example.tenisuj.repository.UserRepository;
import com.example.tenisuj.service.PlayerServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@SpringBootApplication
public class TenisujApplication implements CommandLineRunner {


    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;
    private final LeagueRepository leagueRepository;
    private final PasswordEncoder passwordEncoder;
    private final PlayerServiceBean playerServiceBean;


    @Autowired
    public TenisujApplication(UserRepository userRepository, PlayerRepository playerRepository, MatchRepository matchRepository, LeagueRepository leagueRepository, PasswordEncoder passwordEncoder, PlayerServiceBean playerServiceBean) {
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
        this.leagueRepository = leagueRepository;
        this.passwordEncoder = passwordEncoder;
        this.playerServiceBean = playerServiceBean;
    }


    public static void main(String[] args) {
        SpringApplication.run(TenisujApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        Optional<Player> playerOptional = playerRepository.findByFirstName("Dominika");
        Player player = playerOptional.orElse(null);

        if (player != null) {
            System.out.println("Found player: " + player.getFirstName());
        } else {
            System.out.println("Player not found");
        }






        List<User> users = new ArrayList<>(List.of(
                new User("user", passwordEncoder.encode("user"), Role.USER)
        ));

        List<Player> players = new ArrayList<>(List.of(
                new Player(UUID.randomUUID().toString(), "Marek", "priezvisko", "email@email.com", "Male", LocalDate.now(), true, "right", 100, LocalDate.now()),
                new Player(UUID.randomUUID().toString(), "Michal", "priezvisko", "email@email.com", "Male", LocalDate.now(), true, "right", 50, LocalDate.now()),
                new Player(UUID.randomUUID().toString(), "Viktor", "priezvisko", "email@email.com", "Male", LocalDate.now(), true, "right", 100, LocalDate.now()),
                new Player(UUID.randomUUID().toString(), "Janka", "priezvisko", "email@email.com", "Female", LocalDate.now(), true, "left", 0, LocalDate.now()),
                new Player(UUID.randomUUID().toString(), "Dominika", "priezvisko", "email@email.com", "Female", LocalDate.now(), true, "left", 0, LocalDate.now())
        ));

        List<Match> matches = new ArrayList<>(List.of(
                new Match(UUID.randomUUID().toString(), players.get(0), players.get(1), "Stara cesta 5, Bratislava", LocalDateTime.of(2025, 1, 15, 10, 30), 7, 5, 6, 4, 4, 3, 2, 4, 5, 6, players.get(1), players.get(0)),
                new Match(UUID.randomUUID().toString(), players.get(1), players.get(2), "Popradská 84, Košice", LocalDateTime.of(2025, 1, 25, 9, 30), 7, 6, 0, 6, 3, 0, 6, 2, 3, 6, players.get(1), players.get(2)),
                new Match(UUID.randomUUID().toString(), players.get(3), players.get(4), "Kollárova 85A, Martin", LocalDateTime.of(2025, 2, 10, 11, 30), 4, 3, 6, 5, 1, 6, 5, 5, 4, 6, players.get(3), players.get(4)),
                new Match(UUID.randomUUID().toString(), players.get(3), players.get(1), "Za Císařským mlýnem 2, Praha", LocalDateTime.of(2025, 2, 15, 13, 30), 5, 6, 4, 3, 5, 2, 3, 6, 6, 2, players.get(1), players.get(3))
                //new Match(UUID.randomUUID().toString(), playerName, players.get(1), "Glinkova 23, Praha", LocalDateTime.of(2025, 2, 15, 13, 30), 5, 6, 4, 3, 5, 2, 3, 6, 6, 2, players.get(1), players.get(3))
        ));


        List<League> leagues = new ArrayList<>();
        leagues.add(new League(UUID.randomUUID().toString(), "Slovak Tennis League", new ArrayList<>(players), new ArrayList<>(matches)));
        //leagues.add(new League(UUID.randomUUID().toString(), "Czech Tennis League", new ArrayList<>(players), new ArrayList<>(matches)));

        userRepository.saveAll(users);
        playerRepository.saveAll(players);
        matchRepository.saveAll(matches);
        leagueRepository.saveAll(leagues);


    }

}
