package se.bytebase.vr_server.facade;

import se.bytebase.vr_server.model.ScoreModel;
import se.bytebase.vr_server.repository.ScoreRepository;

import java.util.List;

public class ScoreFacade {

  public static ScoreModel addScore(String username, int score, ScoreRepository repo) {
    // Validation logic
    if (username == null || username.isEmpty())
      return null;

    ScoreModel scoreModel = new ScoreModel(username, score);
    return repo.save(scoreModel);
  }

  public static List<ScoreModel> getAllScores(ScoreRepository repo) {
    return repo.findAll();
  }

  public static List<ScoreModel> getScoresByUser(String username, ScoreRepository repo) {
    return repo.findByUsername(username);
  }
}
