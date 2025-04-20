package com.example.tenisuj.controller.rest.AuthController;
import com.example.tenisuj.model.Match;
import com.example.tenisuj.model.Player;
import com.example.tenisuj.repository.MatchRepository;
import com.example.tenisuj.repository.PlayerRepository;
import com.example.tenisuj.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.tenisuj.model.Match;
import com.example.tenisuj.model.Player;
import com.example.tenisuj.repository.MatchRepository;
import com.example.tenisuj.repository.PlayerRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;
    private MatchRepository matchRepository;
    private PlayerRepository playerRepository;

    public MatchController(MatchService matchService, MatchRepository matchRepository, PlayerRepository playerRepository) {
        this.matchService = matchService;
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
    }

    @GetMapping("/getMatches")
    public List<Match> getMatches() {
        return matchRepository.findAll().stream().toList();
    }

    @PostMapping("/create")
    public Match createMatch(HttpServletRequest request) {
        try {
            // Read the request body into a string
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String requestBody = sb.toString();

            // Debugging: print request body
            System.out.println("Request Body: " + requestBody);

            // Manually parse the JSON string
            Map<String, Object> matchData = parseJson(requestBody);

            // Debugging: print parsed data
            System.out.println("Parsed Data: " + matchData);

            // Extract data from the parsed JSON
            String player1FirstName = (String) matchData.get("player1");
            String player2FirstName = (String) matchData.get("player2");
            List<Integer> leftSets = parseIntegerList((List<?>) matchData.get("leftSets"));
            List<Integer> rightSets = parseIntegerList((List<?>) matchData.get("rightSets"));

            // Debugging: print extracted data
            System.out.println("Player 1: " + player1FirstName + ", Player 2: " + player2FirstName);
            System.out.println("Left Sets: " + leftSets);
            System.out.println("Right Sets: " + rightSets);

            // Find players by their first names
            Player player1 = playerRepository.findByFirstName(player1FirstName)
                    .orElseThrow(() -> new RuntimeException("Player not found"));
            Player player2 = playerRepository.findByFirstName(player2FirstName)
                    .orElseThrow(() -> new RuntimeException("Player not found"));

            // Create a new Match object using the constructor
            Match match = new Match(
                    UUID.randomUUID().toString(),
                    player1,
                    player2,
                    "Example Location",
                    LocalDateTime.now(),
                    leftSets.size() > 0 ? leftSets.get(0) : null,
                    rightSets.size() > 0 ? rightSets.get(0) : null,
                    leftSets.size() > 1 ? leftSets.get(1) : null,
                    rightSets.size() > 1 ? rightSets.get(1) : null,
                    leftSets.size() > 2 ? leftSets.get(2) : null,
                    rightSets.size() > 2 ? rightSets.get(2) : null,
                    leftSets.size() > 3 ? leftSets.get(3) : null,
                    rightSets.size() > 3 ? rightSets.get(3) : null,
                    leftSets.size() > 4 ? leftSets.get(4) : null,
                    rightSets.size() > 4 ? rightSets.get(4) : null,
                    null,
                    null
            );

            // Save the match
            matchRepository.save(match);

            // Debugging: print confirmation
            System.out.println("Match added");

            return match;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read the request body", e);
        }
    }

    private Map<String, Object> parseJson(String jsonString) {
        // Simple JSON parser (you can replace this with a library if needed)
        // This is just a basic example for illustration purposes
        Map<String, Object> map = new HashMap<>();
        jsonString = jsonString.replace("{", "").replace("}", "").replaceAll("\"", "");
        String[] pairs = jsonString.split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            map.put(keyValue[0].trim(), keyValue[1].trim());
        }
        return map;
    }

    private List<Integer> parseIntegerList(List<?> list) {
        List<Integer> integers = new ArrayList<>();
        for (Object obj : list) {
            integers.add(Integer.parseInt(obj.toString()));
        }
        return integers;
    }
}
