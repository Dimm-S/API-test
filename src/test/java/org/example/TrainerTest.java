package org.example;

import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TrainerTest {

    @Test
    public void checkResponceCodeTest() {
        Specifications.setSpecs(Specifications.requestSpec());
        TrainerData trainer = given()
                .queryParam("trainer_id", "11299")
                .when()
                .get("/v2/trainers")
                .then().log().all()
                .statusCode(200)
                .extract().body().jsonPath().getObject("data[0]", TrainerData.class);

        Assert.assertEquals("11299", trainer.getId());
        Assert.assertEquals("Skating Bulb", trainer.getTrainer_name());
    }
}

