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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
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
                new User("user", passwordEncoder.encode("user"), Role.USER)
        ));

        List<Player> players = new LinkedList<>(List.of(
                new Player(UUID.randomUUID().toString(), "Marek", "priezvisko", "email@email.com", "Male", LocalDate.now(), true, "Right", 100, LocalDate.now()),
                new Player(UUID.randomUUID().toString(), "Michal", "priezvisko", "email@email.com", "Male", LocalDate.now(), true, "Right", 50, LocalDate.now()),
                new Player(UUID.randomUUID().toString(), "Viktor", "priezvisko", "email@email.com", "Male", LocalDate.now(), true, "Right", 100, LocalDate.now()),
                new Player(UUID.randomUUID().toString(), "Janka", "priezvisko", "email@email.com", "Female", LocalDate.now(), true, "Left", 75, LocalDate.now()),
                new Player(UUID.randomUUID().toString(), "Dominika", "priezvisko", "email@email.com", "Female", LocalDate.now(), true, "Left", 75, LocalDate.now())
        ));

        List<Match> matchesSK = new LinkedList<>(List.of(
                new Match(UUID.randomUUID().toString(), players.get(0), players.get(1), "Stara cesta 5, Bratislava,", LocalDateTime.of(2025, 1, 15, 10, 30), 7, 5, 6, 4, 5, 6, 3, 6, 4, 3, null, players.get(0)),
                new Match(UUID.randomUUID().toString(), players.get(1), players.get(2), "Popradska 84, Košice", LocalDateTime.of(2025, 1, 31, 11, 30), 5, 5, 4, 6, 3, 0, 5, 2, 4, 7, null, players.get(1)),
                new Match(UUID.randomUUID().toString(), players.get(3), players.get(4), "Kollarova 85A, Martin", LocalDateTime.of(2025, 2, 12, 9, 30), 7, 4, 4, 6, 3, 2, 7, 5, 4, 6, null, players.get(2)),
                new Match(UUID.randomUUID().toString(), players.get(3), players.get(1), "Čachovský rad 61, Vrútky", LocalDateTime.of(2025, 2, 15, 13, 00), 7, 3, 1, 4, 3, 2, 3, 6, 4, 5, null, players.get(4))
        ));

        List<Match> matchesCZ = new LinkedList<>(List.of(
                new Match(UUID.randomUUID().toString(), players.get(3), players.get(1), "Magistrů 24, Praha", LocalDateTime.of(2025, 1, 23, 14, 00), 7, 3, 1, 4, 3, 2, 3, 6, 4, 5, null, players.get(4)),
                new Match(UUID.randomUUID().toString(), players.get(3), players.get(1), "Za Cisarskym mlynem 2, Praha", LocalDateTime.of(2025, 2, 12, 15, 00), 7, 3, 1, 4, 3, 2, 3, 6, 4, 5, null, players.get(4))
        ));

        List<League> leagues = new ArrayList<>(List.of(
                new League(UUID.randomUUID().toString(), "Slovak Tennis League", new ArrayList<>(players), new ArrayList<>(matchesSK)),
                new League(UUID.randomUUID().toString(), "Czech Tennis League", new ArrayList<>(players), new ArrayList<>(matchesCZ))
        ));


        userRepository.saveAll(users);
        playerRepository.saveAll(players);
        matchRepository.saveAll(matchesSK);
        matchRepository.saveAll(matchesCZ);
        leagueRepository.saveAll(leagues);

    }
}
