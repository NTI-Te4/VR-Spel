package se.bytebase.vr_server.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "game_score")
@Data
@NoArgsConstructor
@EntityListeners(org.springframework.data.jpa.domain.support.AuditingEntityListener.class)
public class ScoreModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime uploaded; // This maps to 'uploaded' in the insert statement

    @Column(nullable = false, unique = true) // This was a previous potential issue, but not the current 500
    private String username;

    @Column(nullable = false)
    private int score;

    public ScoreModel(String username, int score) {
        this.username = username;
        this.score = score;
    }
}