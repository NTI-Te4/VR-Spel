package se.bytebase.vr_server.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.bytebase.vr_server.dto.ScoreRequest;
import se.bytebase.vr_server.model.ScoreModel;
import se.bytebase.vr_server.repository.ScoreRepository;
import se.bytebase.vr_server.facade.ScoreFacade;

import java.util.List;

@RestController
@RequestMapping("/api/score")
public class ScoreController {

  private final ScoreRepository scoreRepository;
  private final SimpMessagingTemplate messagingTemplate;

  public ScoreController(ScoreRepository scoreRepository, SimpMessagingTemplate messagingTemplate) {
    this.scoreRepository = scoreRepository;
    this.messagingTemplate = messagingTemplate;
  }

  @PostMapping("/addscore")
  public ResponseEntity<?> addScore(@RequestBody ScoreRequest scoreRequest) {
    ScoreModel savedScore = ScoreFacade.addScore(
        scoreRequest.getUsername(),
        scoreRequest.getScore(),
        scoreRepository);

    if (savedScore != null) {

      List<ScoreModel> allScores = ScoreFacade.getAllScores(scoreRepository);

      messagingTemplate.convertAndSend("/topic/scores", allScores);

      return ResponseEntity.ok("Score added successfully");
    } else {
      return ResponseEntity.internalServerError().body("Failed to add score");
    }
  }

  @GetMapping("/getScore")
  public List<ScoreModel> getScores() {
    return ScoreFacade.getAllScores(scoreRepository);
  }

  @GetMapping("/getScore/{username}")
  public List<ScoreModel> getScoresByName(@PathVariable String username) {
    return ScoreFacade.getScoresByUser(username, scoreRepository);
  }

}
