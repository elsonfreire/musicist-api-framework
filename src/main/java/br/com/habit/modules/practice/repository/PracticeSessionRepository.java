package br.com.habit.modules.practice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.habit.modules.practice.model.PracticeSession;

public interface PracticeSessionRepository extends JpaRepository<PracticeSession, UUID> {
  List<PracticeSession> findByUserId(UUID userId);
}
