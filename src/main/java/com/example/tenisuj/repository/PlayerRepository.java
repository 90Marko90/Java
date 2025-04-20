package com.example.tenisuj.repository;

import com.example.tenisuj.model.Player;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
    Optional<Player> findByFirstName(String firstName);

    String firstName(@NotBlank(message = "First name can not be empty") String firstName);
}
