package se.bytebase.vr_server.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import se.bytebase.vr_server.dto.RankedScoreDto;
import se.bytebase.vr_server.facade.ScoreFacade;
import se.bytebase.vr_server.model.ScoreModel;
import se.bytebase.vr_server.repository.ScoreRepository;

import java.util.List;

@Controller
public class ScoreWebSocketController {
    private final ScoreRepository scoreRepository;

    public ScoreWebSocketController(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/scores")
    public List<ScoreModel> greeting() throws Exception {
        return ScoreFacade.getAllScores(scoreRepository);
    }
}