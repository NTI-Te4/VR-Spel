package se.bytebase.vr_server.facade;

import se.bytebase.vr_server.dto.RankedScoreDto;
import se.bytebase.vr_server.model.ScoreModel;
import se.bytebase.vr_server.repository.ScoreRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
  

  
  public static List<RankedScoreDto> getRankedScoresWithRanksProper(ScoreRepository repo) {
    List<ScoreModel> allScores = repo.findAll();
    
    List<ScoreModel> sortedScores = allScores.stream()
        .sorted((s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()))
        .collect(Collectors.toList());
    
    List<RankedScoreDto> rankedScores = new java.util.ArrayList<>();
    for (int i = 0; i < sortedScores.size(); i++) {
        ScoreModel score = sortedScores.get(i);
        int rank = i + 1;
        rankedScores.add(new RankedScoreDto(score.getUsername(), score.getScore(), rank));
    }
    return rankedScores;
  }

  public static List<ScoreModel> getScoresByUser(String username, ScoreRepository repo) {
    return repo.findByUsername(username);
  }
}
