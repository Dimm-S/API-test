package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreatePokemon {
    private String name;
    private Integer photo_id;
}
