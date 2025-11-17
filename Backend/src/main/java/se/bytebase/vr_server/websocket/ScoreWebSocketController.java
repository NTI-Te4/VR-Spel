package se.bytebase.vr_server.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import se.bytebase.vr_server.dto.RankedScoreDto;
import se.bytebase.vr_server.facade.ScoreFacade;
import se.bytebase.vr_server.model.ScoreModel;
import se.bytebase.vr_server.repository.ScoreRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
public class ScoreWebSocketController {
    
    private static final Logger logger = LoggerFactory.getLogger(ScoreWebSocketController.class);
    
    private final ScoreRepository scoreRepository;

    public ScoreWebSocketController(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/scores")
    public List<ScoreModel> greeting() throws Exception {
        logger.info("WebSocket request received to get all scores");
        try {
            List<ScoreModel> scores = ScoreFacade.getAllScores(scoreRepository);
            logger.info("WebSocket: Sending {} scores to client", scores.size());
            return scores;
        } catch (Exception e) {
            logger.error("Error in WebSocket greeting method: {}", e.getMessage(), e);
            return List.of();
        }
    }
}