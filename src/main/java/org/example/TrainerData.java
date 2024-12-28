package org.example;

import lombok.Getter;

import java.util.List;

@Getter
public class TrainerData {
    private String id;
    private String trainer_name;
    private String level;
    private List<String> pokemons;
    private List<String> pokemons_alive;
    private List<PokemonData> pokemons_in_pokeballs;
    private String get_history_battle;
    private Boolean is_premium;
    private Integer premium_duration;
    private Integer avatar_id;
    private String city;
}
