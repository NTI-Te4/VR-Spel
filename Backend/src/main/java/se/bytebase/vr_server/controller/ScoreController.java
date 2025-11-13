package se.bytebase.vr_server.controller;

import se.bytebase.vr_server.dto.ScoreRequest;
import se.bytebase.vr_server.model.ScoreModel;
import se.bytebase.vr_server.repository.ScoreRepository;
import se.bytebase.vr_server.facade.ScoreFacade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/score")
public class ScoreController {

    private final ScoreRepository scoreRepository;

    public ScoreController(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @PostMapping("/addscore")
    public ResponseEntity<?> addScore(@RequestBody ScoreRequest scoreRequest) {
        boolean success = ScoreFacade.addScore(
                scoreRequest.getUsername(),
                scoreRequest.getScore(),
                scoreRequest.getTime(),
                scoreRepository
        );

        if (success) {
            return ResponseEntity.ok("Score added successfully");
        } else {
            return ResponseEntity.internalServerError().body("Failed to add score");
        }
    }

    @GetMapping("/getScore")
    public List<ScoreModel> getScores() {
        return scoreRepository.findAll();
    }

    @GetMapping("/getScore/{username}")
    public List<ScoreModel> getScoresByName(@PathVariable String username) {
        return scoreRepository.findByUsername(username);
    }
}
