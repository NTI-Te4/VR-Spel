package se.bytebase.vr_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankedScoreDto {
    private String username;
    private int score;
    private int rank;
    private LocalDateTime date;
}