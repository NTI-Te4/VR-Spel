package se.bytebase.vr_server.facade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.bytebase.vr_server.dto.ScoreRequest;
import se.bytebase.vr_server.model.ScoreModel;
import se.bytebase.vr_server.repository.ScoreRepository;

import java.util.Arrays;
import java.util.List;

public class ScoreFacade {
    public static boolean addScore(String username, int score, String time, ScoreRepository scoreRepository) {
        ScoreModel scoreModel = new ScoreModel(
                username, score, time
        );
        scoreRepository.save(scoreModel);
        return true;
    }
}
