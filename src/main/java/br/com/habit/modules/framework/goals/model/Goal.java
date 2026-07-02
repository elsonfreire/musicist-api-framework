package br.com.habit.modules.framework.goals.model;

import br.com.habit.modules.framework.goals.enums.GoalStatusType;
import br.com.habit.modules.framework.shared.model.UserOwnedEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "goals")
public class Goal extends UserOwnedEntity {
  @Column(nullable = false)
  private String title;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private GoalStatusType status;

  private LocalDateTime concludedAt;

  @Column(nullable = false)
  private LocalDateTime updatedAt;

  @PrePersist
  public void prePersist() {
    this.updatedAt = LocalDateTime.now();
    this.status = GoalStatusType.PENDING;
  }

  @PreUpdate
  public void preUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
}
