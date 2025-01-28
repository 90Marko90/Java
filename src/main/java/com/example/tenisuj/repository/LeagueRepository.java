package com.example.tenisuj.repository;

import com.example.tenisuj.model.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends JpaRepository<League, String> {
    boolean existsByName(String name);
}
