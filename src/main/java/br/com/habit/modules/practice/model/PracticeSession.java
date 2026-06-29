package br.com.habit.modules.practice.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.musicist.enums.InstrumentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "practice_sessions")
public class PracticeSession {
  @Id
  @UuidGenerator(style = UuidGenerator.Style.VERSION_7)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false)
  private LocalDate date;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private InstrumentType instrument;

  @Column(nullable = false)
  private Integer durationMinutes;

  private String notes;

  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @PrePersist
  public void prePersist() {
    this.createdAt = LocalDateTime.now();
  }

  public PracticeSession(
      Integer durationMinutes, InstrumentType instrument, String notes, LocalDate date, User user) {
    this.durationMinutes = durationMinutes;
    this.instrument = instrument;
    this.notes = notes;
    this.date = date;
    this.user = user;
  }
}
