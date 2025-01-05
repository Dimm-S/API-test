package org.example;

import org.example.models.*;

import java.util.Collections;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Requests {

    /**
     * Получить покемона
     * @param id id покемона
     * @return экземпляр покемона
     */
    public PokemonData getPokemon(Integer id) {
        return given()
                .queryParam("pokemon_id", id)
                .when()
                .get("/v2/pokemons")
                .then().log().body()
                .statusCode(200)
                .extract().body().jsonPath().getObject("data[0]", PokemonData.class);
    }

    /**
     * Создать покемона
     * @param name имя покемона
     * @param photoId id фото покемона
     * @return response body
     */
    public StandartResponse createPokemon(String name, Integer photoId) {
        return given()
                .body(new CreatePokemon(name, photoId))
                .when()
                .post("/v2/pokemons")
                .then().log().body()
                .statusCode(201)
                .extract().as(StandartResponse.class);
    }

    /**
     * Обновить покемона
     * @param id id покемона
     * @param name имя покемона
     * @param photoId id фото покемона
     * @return response body
     */
    public StandartResponse updatePokemon(String id, String name, Integer photoId) {
        return given()
                .body(new UpdatePokemon(id, name, photoId))
                .when()
                .put("/v2/pokemons")
                .then().log().body()
                .statusCode(200)
                .extract().as(StandartResponse.class);
    }

    /**
     * Добавить покемона в покеболл
     * @param id id покемона
     * @return response body
     */
    public StandartResponse addPokemonInPokeball(String id) {
        return given()
                .body(new HashMap<>(Collections.singletonMap("pokemon_id", id)))
                .when()
                .post("/v2/trainers/add_pokeball")
                .then().log().body()
                .statusCode(200)
                .extract().as(StandartResponse.class);
    }

    /**
     * Отправить покемона в нокаут
     * @param id id покемона
     */
    public void killPokemon(String id) {
        given()
            .body(new HashMap<>(Collections.singletonMap("pokemon_id", id)))
            .when()
            .post("v2/pokemons/knockout")
            .then().log().body()
            .statusCode(200);
    }

    /**
     * Получить информацию о трененре
     * @param id
     * @return
     */
    public TrainerData getTrainer(String id) {
        return given()
                .queryParam("trainer_id", id)
                .when()
                .get("/v2/trainers")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getObject("data[0]", TrainerData.class);
    }
}
