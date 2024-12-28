package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Specifications {

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://api.pokemonbattle.ru")
                .addHeader("trainer_token", System.getenv("TRAINER_TOKEN"))
                .setContentType(ContentType.JSON)
                .build();
    }

    public static void setSpecs(RequestSpecification request) {
        RestAssured.requestSpecification = request;
    }
}
