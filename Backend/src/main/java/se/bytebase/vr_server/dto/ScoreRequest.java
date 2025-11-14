package se.bytebase.vr_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScoreRequest {
    private String username;
    private int score;
    private String time;
}