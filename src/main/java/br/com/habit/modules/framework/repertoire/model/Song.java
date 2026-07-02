package br.com.habit.modules.framework.repertoire.model;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.framework.repertoire.enums.DifficultyType;
import br.com.habit.modules.framework.repertoire.enums.LearningStatusType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
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
@Table(name = "songs")
public class Song {
  @Id
  @UuidGenerator(style = UuidGenerator.Style.VERSION_7)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private LearningStatusType status;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String artist;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private DifficultyType difficulty;

  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @PrePersist
  public void prePersist() {
    this.createdAt = LocalDateTime.now();
  }

  public Song(
      User user,
      LearningStatusType status,
      String title,
      String artist,
      DifficultyType difficulty) {
    this.user = user;
    this.status = status;
    this.title = title;
    this.artist = artist;
    this.difficulty = difficulty;
  }
}
