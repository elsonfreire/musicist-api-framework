package br.com.musicist.modules.practice.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.musicist.modules.musicist.enums.InstrumentType;
import br.com.musicist.modules.practice.model.PracticeSession;

public record PracticeSessionResponse(
    UUID id,
    LocalDate date,
    InstrumentType instrument,
    Integer durationMinutes,
    String notes,
    LocalDateTime createdAt) {
  public PracticeSessionResponse(PracticeSession practiceSession) {
    this(
        practiceSession.getId(),
        practiceSession.getDate(),
        practiceSession.getInstrument(),
        practiceSession.getDurationMinutes(),
        practiceSession.getNotes(),
        practiceSession.getCreatedAt());
  }
}
