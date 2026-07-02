package br.com.habit.modules.framework.goals.model;

import br.com.habit.modules.framework.goals.enums.GoalStatusType;
import br.com.habit.modules.framework.shared.model.UserOwnedEntity;
import br.com.habit.modules.framework.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

  public Goal(User user, String title) {
    this.user = user;
    this.title = title;
  }

  public void updateStatus(GoalStatusType newStatus) {
    this.status = newStatus;
  }
}
