package se.bytebase.vr_server.facade;

import se.bytebase.vr_server.dto.RankedScoreDto;
import se.bytebase.vr_server.model.ScoreModel;
import se.bytebase.vr_server.repository.ScoreRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ScoreFacade {
  
  private static final Logger logger = LoggerFactory.getLogger(ScoreFacade.class);

  public static ScoreModel addScore(String username, int score, ScoreRepository repo) {
    if (username == null || username.isEmpty()) {
      logger.warn("Attempt to add score with empty/null username");
      return null;
    }

    String uniqueUsername = generateUniqueUsername(username, repo);
    
    ScoreModel scoreModel = new ScoreModel(uniqueUsername, score);
    try {
      ScoreModel savedScore = repo.save(scoreModel);
      logger.info("Score added successfully for user: {} with score: {}", uniqueUsername, score);
      return savedScore;
    } catch (Exception e) {
      logger.error("Failed to add score for user: {} - Error: {}", uniqueUsername, e.getMessage());
      return null;
    }
  }

  private static String generateUniqueUsername(String username, ScoreRepository repo) {
    String baseUsername = username;
    int counter = 1;
    String currentUsername = username;
    
    List<ScoreModel> existingScores = repo.findByUsername(currentUsername);
    if (existingScores.isEmpty()) {
      return currentUsername;
    }
    
    while (!repo.findByUsername(currentUsername).isEmpty()) {
      currentUsername = baseUsername + counter;
      counter++;
      if (counter > 1000) {
        logger.error("Too many users with similar names to '{}', generating random suffix", baseUsername);
        currentUsername = baseUsername + "_" + System.currentTimeMillis();
        break;
      }
    }
    
    if (!currentUsername.equals(username)) {
      logger.info("Username '{}' already exists, changed to '{}'", username, currentUsername);
    }
    
    return currentUsername;
  }

  public static List<ScoreModel> getAllScores(ScoreRepository repo) {
    try {
      List<ScoreModel> scores = repo.findAll();
      logger.info("Retrieved {} scores from database", scores.size());
      return scores;
    } catch (Exception e) {
      logger.error("Failed to retrieve all scores - Error: {}", e.getMessage());
      return List.of();
    }
  }
  

  
  public static List<RankedScoreDto> getRankedScoresWithRanksProper(ScoreRepository repo) {
    try {
      List<ScoreModel> allScores = repo.findAll();
      
      List<ScoreModel> sortedScores = allScores.stream()
          .sorted((s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()))
          .collect(Collectors.toList());
      
      List<RankedScoreDto> rankedScores = new java.util.ArrayList<>();
      for (int i = 0; i < sortedScores.size(); i++) {
          ScoreModel score = sortedScores.get(i);
          int rank = i + 1;
          rankedScores.add(new RankedScoreDto(score.getUsername(), score.getScore(), rank, score.getUploaded()));
      }
      
      logger.info("Generated ranked scores for {} entries", rankedScores.size());
      return rankedScores;
    } catch (Exception e) {
      logger.error("Failed to generate ranked scores - Error: {}", e.getMessage());
      return List.of();
    }
  }

  public static List<ScoreModel> getScoresByUser(String username, ScoreRepository repo) {
    try {
      List<ScoreModel> scores = repo.findByUsername(username);
      logger.info("Retrieved {} scores for user: {}", scores.size(), username);
      return scores;
    } catch (Exception e) {
      logger.error("Failed to retrieve scores for user: {} - Error: {}", username, e.getMessage());
      return List.of();
    }
  }
  
  public static List<RankedScoreDto> getRankedScoresByUser(String username, ScoreRepository repo) {
    try {
      List<ScoreModel> allScores = repo.findAll();
      
      List<ScoreModel> sortedScores = allScores.stream()
          .sorted((s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()))
          .collect(Collectors.toList());
      
      List<RankedScoreDto> rankedScores = new java.util.ArrayList<>();
      for (int i = 0; i < sortedScores.size(); i++) {
          ScoreModel score = sortedScores.get(i);
          if (score.getUsername().equals(username)) {
              int rank = i + 1;
              rankedScores.add(new RankedScoreDto(score.getUsername(), score.getScore(), rank, score.getUploaded()));
          }
      }
      
      logger.info("Retrieved {} ranked scores for user: {}", rankedScores.size(), username);
      return rankedScores;
    } catch (Exception e) {
      logger.error("Failed to retrieve ranked scores for user: {} - Error: {}", username, e.getMessage());
      return List.of();
    }
  }
}
