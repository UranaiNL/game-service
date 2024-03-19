package com.replay.gameservice.service;

import com.replay.gameservice.dto.CreateCharacterRequest;
import com.replay.gameservice.dto.CharacterResponse;
import com.replay.gameservice.model.Character;
import com.replay.gameservice.model.Game;
import com.replay.gameservice.repository.CharacterRepository;
import com.replay.gameservice.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final GameRepository gameRepository;

    public Character createCharacter(CreateCharacterRequest createCharacterRequest) throws Exception {
        Character newCharacter = new Character();
        newCharacter.setName(createCharacterRequest.getName());
        newCharacter.setImageRender(createCharacterRequest.getImageRender());
        Optional<Game> foundGame = gameRepository.findById(createCharacterRequest.getGameId());
        if(foundGame.isPresent()) {
            newCharacter.setGame(foundGame.get());
            return characterRepository.save(newCharacter);
        } else {
            throw new Exception("Game not found for ID: " + createCharacterRequest.getGameId());
        }
    }

    public CharacterResponse getCharacterById(Long characterId){
        Optional<Character> foundCharacter = characterRepository.findById(characterId);
        return foundCharacter.map(this::mapToCharacterResponse).orElse(null);
    }

    public List<CharacterResponse> getAllCharactersByGameId(Long gameId){
        List<Character> foundCharacters = characterRepository.findAllByGameId(gameId);
        return foundCharacters.stream().map(this::mapToCharacterResponse).toList();
    }

    private CharacterResponse mapToCharacterResponse (Character character){
        return CharacterResponse.builder()
                .id(character.getId())
                .name(character.getName())
                .imageRender(character.getImageRender())
                .game(character.getGame())
                .build();
    }
}