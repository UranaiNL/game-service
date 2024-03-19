package com.replay.gameservice.dto;

import com.replay.gameservice.model.Game;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharacterResponse {
    private Long id;
    private String name;
    private String imageRender;
    private Game game;
}
