package br.com.musicist.modules.practice_session.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.musicist.common.enums.InstrumentType;
import br.com.musicist.modules.practice_session.model.PracticeSession;

public record PracticeSessionResponse(
    Long id,
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
