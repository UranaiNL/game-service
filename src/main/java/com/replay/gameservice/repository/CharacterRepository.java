package com.replay.gameservice.repository;

import com.replay.gameservice.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findAllByGameId(Long gameId);
}
