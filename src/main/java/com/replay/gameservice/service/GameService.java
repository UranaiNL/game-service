package com.replay.gameservice.service;

import com.replay.gameservice.dto.CreateGameRequest;
import com.replay.gameservice.dto.GameResponse;
import com.replay.gameservice.model.Game;
import com.replay.gameservice.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public Game createGame(CreateGameRequest createGameRequest){
        Game newGame = new Game();
        newGame.setName(createGameRequest.getName());
        newGame.setImageRender(createGameRequest.getImageRender());
        return gameRepository.save(newGame);
    }

    public GameResponse getGameById(Long gameId){
        Optional<Game> foundGame = gameRepository.findById(gameId);
        return foundGame.map(this::mapToGameResponse).orElse(null);
    }

    private GameResponse mapToGameResponse(Game game){
        return GameResponse.builder()
                .id(game.getId())
                .name(game.getName())
                .imageRender(game.getImageRender())
                .build();
    }
}
