package se.bytebase.vr_server.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.bytebase.vr_server.dto.RankedScoreDto;
import se.bytebase.vr_server.dto.ScoreRequest;
import se.bytebase.vr_server.model.ScoreModel;
import se.bytebase.vr_server.repository.ScoreRepository;
import se.bytebase.vr_server.facade.ScoreFacade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/score")
public class ScoreController {

  private static final Logger logger = LoggerFactory.getLogger(ScoreController.class);
  
  private final ScoreRepository scoreRepository;
  private final SimpMessagingTemplate messagingTemplate;

  public ScoreController(ScoreRepository scoreRepository, SimpMessagingTemplate messagingTemplate) {
    this.scoreRepository = scoreRepository;
    this.messagingTemplate = messagingTemplate;
  }

  @PostMapping("/addscore")
  public ResponseEntity<?> addScore(@RequestBody ScoreRequest scoreRequest) {
    try {
      logger.info("Received request to add score for user: {} with score: {}", 
                 scoreRequest.getUsername(), scoreRequest.getScore());
      
      // Validate the request
      if (scoreRequest.getUsername() == null || scoreRequest.getUsername().trim().isEmpty()) {
        logger.warn("Invalid request: Username is null or empty");
        return ResponseEntity.badRequest().body("Username cannot be null or empty");
      }
      
      if (scoreRequest.getScore() < 0) {
        logger.warn("Invalid request: Score cannot be negative, received: {}", scoreRequest.getScore());
        return ResponseEntity.badRequest().body("Score cannot be negative");
      }
      
      ScoreModel savedScore = ScoreFacade.addScore(
          scoreRequest.getUsername(),
          scoreRequest.getScore(),
          scoreRepository);

      if (savedScore != null) {
        List<RankedScoreDto> rankedScores = ScoreFacade.getRankedScoresWithRanksProper(scoreRepository);
        
        // Log that the score was added successfully
        logger.info("Score added successfully for user: {} with final username: {}", 
                   scoreRequest.getUsername(), savedScore.getUsername());
        
        messagingTemplate.convertAndSend("/topic/rankedScores", rankedScores);
        
        return ResponseEntity.ok("Score added successfully for user: " + savedScore.getUsername());
      } else {
        logger.error("Failed to add score for user: {}", scoreRequest.getUsername());
        return ResponseEntity.status(500).body("Failed to add score to database");
      }
    } catch (Exception e) {
      logger.error("Error occurred while adding score for user: {}: {}", 
                  scoreRequest.getUsername(), e.getMessage(), e);
      return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
    }
  }

  @GetMapping("/getScore")
  public ResponseEntity<?> getAllScores() {
    try {
      List<ScoreModel> scores = ScoreFacade.getAllScores(scoreRepository);
      logger.info("Retrieved {} scores from database", scores.size());
      return ResponseEntity.ok(scores);
    } catch (Exception e) {
      logger.error("Error occurred while retrieving scores: {}", e.getMessage(), e);
      return ResponseEntity.status(500).body("Failed to retrieve scores: " + e.getMessage());
    }
  }
  
  @GetMapping("/getRankedScores")
  public ResponseEntity<?> getRankedScores() {
    try {
      List<RankedScoreDto> rankedScores = ScoreFacade.getRankedScoresWithRanksProper(scoreRepository);
      logger.info("Retrieved {} ranked scores", rankedScores.size());
      return ResponseEntity.ok(rankedScores);
    } catch (Exception e) {
      logger.error("Error occurred while retrieving ranked scores: {}", e.getMessage(), e);
      return ResponseEntity.status(500).body("Failed to retrieve ranked scores: " + e.getMessage());
    }
  }

  // Get scores for a specific user WITHOUT rank information
  @GetMapping("/getScore/{username}")
  public ResponseEntity<?> getScoresByName(@PathVariable String username) {
    try {
      logger.info("Retrieving scores for user (without rank): {}", username);
      List<ScoreModel> scores = ScoreFacade.getScoresByUser(username, scoreRepository);
      logger.info("Found {} scores for user: {}", scores.size(), username);
      return ResponseEntity.ok(scores);
    } catch (Exception e) {
      logger.error("Error occurred while retrieving scores for user {}: {}", username, e.getMessage(), e);
      return ResponseEntity.status(500).body("Failed to retrieve scores for user: " + e.getMessage());
    }
  }
  
  // Get scores for a specific user WITH rank information
  @GetMapping("/getRankedScore/{username}")
  public ResponseEntity<?> getRankedScoresByName(@PathVariable String username) {
    try {
      logger.info("Retrieving ranked scores for user: {}", username);
      List<RankedScoreDto> rankedScores = ScoreFacade.getRankedScoresByUser(username, scoreRepository);
      logger.info("Found {} ranked scores for user: {}", rankedScores.size(), username);
      return ResponseEntity.ok(rankedScores);
    } catch (Exception e) {
      logger.error("Error occurred while retrieving ranked scores for user {}: {}", username, e.getMessage(), e);
      return ResponseEntity.status(500).body("Failed to retrieve ranked scores for user: " + e.getMessage());
    }
  }
}
