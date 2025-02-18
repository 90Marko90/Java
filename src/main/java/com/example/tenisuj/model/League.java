package com.example.tenisuj.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity(name = "leagues")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class League {
    @Id
    private String id;
    private String name;
    @ManyToMany
    private List<Player> players;
    @ManyToMany
    private List<Match> matches;
}
