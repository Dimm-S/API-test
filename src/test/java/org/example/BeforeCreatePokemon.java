package org.example;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BeforeCreatePokemon {

    Requests requests = new Requests();

    public void setSpecsAndKillAllAlivePokemons() {
        Specifications.setSpecs(Specifications.requestSpec());

        List<String> alivePokemons = getAlivePokemons();
        killAlivePokemons(alivePokemons);
    }

    private List<String> getAlivePokemons() {
        return given()    // получить список живых покемонов
                .when()
                .get("/v2/me")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getList("data[0].pokemons_alive");
    }

    private void killAlivePokemons(List<String> pokemonsIds) {
        if (pokemonsIds.size() != 0)      // убить всех покемонов
            for (String pokemonId : pokemonsIds) {
                requests.killPokemon(pokemonId);
            }
    }

}
