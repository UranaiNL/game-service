package com.replay.gameservice.controller;

import com.replay.gameservice.dto.CreateCharacterRequest;
import com.replay.gameservice.dto.CharacterResponse;
import com.replay.gameservice.model.Character;
import com.replay.gameservice.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/character")
public class CharacterController {

    private final CharacterService characterService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createCharacter(@RequestBody CreateCharacterRequest createCharacterRequest) {
        try{
            Character createdCharacter = characterService.createCharacter(createCharacterRequest);
            return "Character with name " + createdCharacter.getName() + " from " + createdCharacter.getGame().getName() + " created successfully!";
        } catch(Exception ex){
            return  "Error: " + ex.getMessage();
        }
    }

    @GetMapping("/{characterId}")
    @ResponseStatus(HttpStatus.OK)
    public CharacterResponse getCharacterById(@PathVariable("characterId") Long characterId){
        return characterService.getCharacterById(characterId);
    }

    @GetMapping("/game/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CharacterResponse> getAllCharactersByGameId(@PathVariable("gameId") Long gameId){
        return characterService.getAllCharactersByGameId(gameId);
    }
}
