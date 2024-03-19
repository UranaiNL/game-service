package com.replay.gameservice.controller;

import com.replay.gameservice.dto.CreateGameRequest;
import com.replay.gameservice.dto.GameResponse;
import com.replay.gameservice.model.Game;
import com.replay.gameservice.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createGame(@RequestBody CreateGameRequest createGameRequest) {
        Game createdGame = gameService.createGame(createGameRequest);
        if(createdGame != null){
            return "Game with name " + createdGame.getName() + " created successfully!";
        }
        return "Game Creation Failed!";
    }

    @GetMapping("/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    public GameResponse getGameById(@PathVariable("gameId") Long gameId){
        return gameService.getGameById(gameId);
    }
}