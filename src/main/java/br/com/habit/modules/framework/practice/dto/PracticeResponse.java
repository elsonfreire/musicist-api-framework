package br.com.habit.modules.framework.practice.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.habit.modules.framework.practice.model.Practice;

public record PracticeResponse(
    UUID id,
    LocalDate date,
    Integer durationMinutes,
    String notes,
    LocalDateTime createdAt
) {
    public PracticeResponse(Practice practice) {
        this(
            practice.getId(),
            practice.getDate(),
            practice.getDurationMinutes(),
            practice.getNotes(),
            practice.getCreatedAt()
        );
    }
}