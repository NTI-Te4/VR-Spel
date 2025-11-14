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
  private LocalDateTime Uploaded;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private int score;

  public ScoreModel(String username, int score) {
    this.username = username;
    this.score = score;
  }
}
