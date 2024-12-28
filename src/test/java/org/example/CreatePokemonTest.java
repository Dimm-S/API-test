package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CreatePokemonTest {

    @Before
    public void killAllPokemons() {
        Specifications.setSpecs(Specifications.requestSpec());

        List<String> alivePokemons = given()    // получить список живых покемонов
                .when()
                .get("/v2/me")
                .then().log().body()
                .statusCode(200)
                .extract().body().jsonPath().getList("data[0].pokemons_alive");

        if (alivePokemons.size() != 0)      // убить всех покемонов
            for (String alivePokemon : alivePokemons) {
                given()
                        .body(new HashMap<>(Collections.singletonMap("pokemon_id", alivePokemon)))
                        .when()
                        .post("v2/pokemons/knockout")
                        .then().log().body()
                        .statusCode(200);
            }
    }

    @Test
    public void TestPokemonCreateUpdatePutInPokeball() {

        StandartResponse createResponse = given()    //создание покемона
                .body(new CreatePokemon("AceMaker", 666))
                .when()
                .post("/v2/pokemons")
                .then().log().body()
                .statusCode(201)
                .extract().as(StandartResponse.class);

        Assert.assertEquals("Покемон создан", createResponse.getMessage());
        String id = createResponse.getId();

        PokemonData createdPokemon = getPokemon(Integer.parseInt(id))  ; //получение созданного покемона

        Assert.assertEquals("AceMaker", createdPokemon.getName()); //проверка имени

        StandartResponse updateResponse = given()    //смена имени покемона
                .body(new UpdatePokemon(
                        createdPokemon.getId(),
                        "DoubleFault",
                        createdPokemon.getPhoto_id()
                ))
                .when()
                .put("/v2/pokemons")
                .then().log().body()
                .statusCode(200)
                .extract().as(StandartResponse.class);

        Assert.assertEquals("Информация о покемоне обновлена", updateResponse.getMessage());

        PokemonData updatedPokemon = getPokemon(Integer.parseInt(id));  //получение обновлённого покемона

        Assert.assertEquals("DoubleFault", updatedPokemon.getName()); //проверка имени

        StandartResponse addPokemonInPokeball = given()  //поймать покемона в покеболл
                .body(new HashMap<>(Collections.singletonMap("pokemon_id", updatedPokemon.getId())))
                .when()
                .post("/v2/trainers/add_pokeball")
                .then().log().body()
                .statusCode(200)
                .extract().as(StandartResponse.class);

        Assert.assertEquals("Покемон пойман в покебол", addPokemonInPokeball.getMessage());
    }

    private static PokemonData getPokemon(Integer id) {
        return given()
                .queryParam("pokemon_id", id)
                .when()
                .get("/v2/pokemons")
                .then().log().body()
                .statusCode(200)
                .extract().body().jsonPath().getObject("data[0]", PokemonData.class);
    }
}
