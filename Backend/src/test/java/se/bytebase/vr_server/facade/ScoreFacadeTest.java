package se.bytebase.vr_server.facade;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import se.bytebase.vr_server.dto.RankedScoreDto;
import se.bytebase.vr_server.model.ScoreModel;
import se.bytebase.vr_server.repository.ScoreRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ScoreFacadeTest {

    @Test
    public void testGetRankedScoresWithRanksProper() {
        // Mock repository
        ScoreRepository mockRepo = Mockito.mock(ScoreRepository.class);
        
        // Create test data
        ScoreModel score1 = new ScoreModel("Alice", 100);
        ScoreModel score2 = new ScoreModel("Bob", 250);
        ScoreModel score3 = new ScoreModel("Charlie", 150);
        ScoreModel score4 = new ScoreModel("David", 300);
        
        List<ScoreModel> mockScores = Arrays.asList(score1, score2, score3, score4);
        when(mockRepo.findAll()).thenReturn(mockScores);
        
        // Call the method
        List<RankedScoreDto> rankedScores = ScoreFacade.getRankedScoresWithRanksProper(mockRepo);
        
        // Verify the results
        assertEquals(4, rankedScores.size());
        
        // Check that scores are properly ranked (highest first)
        assertEquals("David", rankedScores.get(0).getUsername());
        assertEquals(300, rankedScores.get(0).getScore());
        assertEquals(1, rankedScores.get(0).getRank());
        
        assertEquals("Bob", rankedScores.get(1).getUsername());
        assertEquals(250, rankedScores.get(1).getScore());
        assertEquals(2, rankedScores.get(1).getRank());
        
        assertEquals("Charlie", rankedScores.get(2).getUsername());
        assertEquals(150, rankedScores.get(2).getScore());
        assertEquals(3, rankedScores.get(2).getRank());
        
        assertEquals("Alice", rankedScores.get(3).getUsername()); 
        assertEquals(100, rankedScores.get(3).getScore());
        assertEquals(4, rankedScores.get(3).getRank());
    }
}