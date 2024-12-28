package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePokemon {
    private String pokemon_id;
    private String name;
    private Integer photo_id;
}

