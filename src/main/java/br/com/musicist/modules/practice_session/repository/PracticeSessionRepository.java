package br.com.musicist.modules.practice_session.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.musicist.modules.practice_session.model.PracticeSession;

@Repository
public interface PracticeSessionRepository extends JpaRepository<PracticeSession, Long> {
  List<PracticeSession> findByUserId(Long userId);
}
