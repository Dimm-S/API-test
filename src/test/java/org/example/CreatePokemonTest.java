package org.example;

import org.example.models.PokemonData;
import org.example.models.StandartResponse;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreatePokemonTest {
    Requests requests = new Requests();
    private static Integer pokemonId;
    private static final String nameOne = "TestNameOne";
    private static final String nameTwo = "TestNameTwo";

    @BeforeClass
    public static void killAllPokemons() {
        BeforeCreatePokemon before = new BeforeCreatePokemon();
        before.setSpecsAndKillAllAlivePokemons();
    }

    @Test
    public void test1CreatePokemon() {
        StandartResponse createResponse = requests.createPokemon(nameOne, 666);    //создание покемона
        pokemonId = Integer.parseInt(createResponse.getId()); //получение id созданного покемона
        PokemonData createdPokemon = requests.getPokemon(pokemonId); //получение созданного покемона
        assertThat(createResponse.getMessage(), equalTo("Покемон создан"));
        assertThat(createdPokemon.getName(), equalTo(nameOne));  //проверка имени
    }

    @Test
    public void test2UpdatePokemon() {
        PokemonData createdPokemon = requests.getPokemon(pokemonId);
        StandartResponse updateResponse = requests.updatePokemon(createdPokemon.getId(), //смена имени покемона
                nameTwo, createdPokemon.getPhoto_id());
        PokemonData updatedPokemon = requests.getPokemon(pokemonId);  //получение обновлённого покемона
        assertThat(updateResponse.getMessage(), equalTo("Информация о покемоне обновлена"));
        assertThat(updatedPokemon.getName(), equalTo(nameTwo)); //проверка имени
    }

    @Test
    public void test3AddPokeball() {
        PokemonData pokemon = requests.getPokemon(pokemonId); //поймать покемона в покеболл
        StandartResponse addedPokemon = requests.addPokemonInPokeball(pokemon.getId());
        assertThat(addedPokemon.getMessage(), equalTo("Покемон пойман в покебол"));
    }
}
