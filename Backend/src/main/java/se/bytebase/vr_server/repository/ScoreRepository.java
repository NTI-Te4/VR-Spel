package se.bytebase.vr_server.repository;

import se.bytebase.vr_server.model.ScoreModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<ScoreModel, Long> {
    List<ScoreModel> findByUsername(String username);
}