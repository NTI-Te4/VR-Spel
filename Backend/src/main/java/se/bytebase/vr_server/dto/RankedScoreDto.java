package se.bytebase.vr_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RankedScoreDto {
    private String username;
    private int score;
    private int rank;
}