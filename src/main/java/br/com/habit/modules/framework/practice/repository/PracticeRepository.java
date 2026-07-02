package br.com.habit.modules.framework.practice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.habit.modules.framework.practice.model.Practice;

public interface PracticeRepository extends JpaRepository<Practice, UUID> {
    List<Practice> findByUserId(UUID userId);
}