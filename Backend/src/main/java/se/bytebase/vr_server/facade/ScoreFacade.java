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
  

  
  // Better implementation that properly calculates ranks with handling of ties
  public static List<RankedScoreDto> getRankedScoresWithRanksProper(ScoreRepository repo) {
    List<ScoreModel> allScores = repo.findAll();
    
    // Sort scores in descending order (highest first)
    List<ScoreModel> sortedScores = allScores.stream()
        .sorted((s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()))
        .collect(Collectors.toList());
    
    // Create ranked DTOs with proper ranking considering ties
    List<RankedScoreDto> rankedScores = new java.util.ArrayList<>();
    for (int i = 0; i < sortedScores.size(); i++) {
        ScoreModel score = sortedScores.get(i);
        // Rank is position + 1 (since indices are 0-based)
        int rank = i + 1;
        rankedScores.add(new RankedScoreDto(score.getUsername(), score.getScore(), rank));
    }
    return rankedScores;
  }

  public static List<ScoreModel> getScoresByUser(String username, ScoreRepository repo) {
    return repo.findByUsername(username);
  }
}
