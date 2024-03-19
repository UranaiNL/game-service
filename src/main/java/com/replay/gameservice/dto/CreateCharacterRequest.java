package com.replay.gameservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCharacterRequest {
    private String name;
    private String imageRender;
    private Long gameId;
}