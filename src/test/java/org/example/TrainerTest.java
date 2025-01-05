package org.example;

import org.example.models.TrainerData;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TrainerTest {
    private final String trainerId = System.getenv("TRAINER_ID");
    private final String trainerName = System.getenv("TRAINER_NAME");

    Requests requests = new Requests();

    @Test
    public void testCheckResponceCode() {
        Specifications.setSpecs(Specifications.requestSpec());
        TrainerData trainer = requests.getTrainer(trainerId);

        assertThat(trainer.getId(), equalTo(trainerId));
        assertThat(trainer.getTrainer_name(), equalTo(trainerName));
    }
}

