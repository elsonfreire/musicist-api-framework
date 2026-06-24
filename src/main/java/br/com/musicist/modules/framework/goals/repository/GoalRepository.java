package br.com.musicist.modules.framework.goals.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.musicist.modules.framework.goals.enums.GoalStatusType;
import br.com.musicist.modules.framework.goals.model.Goal;
import br.com.musicist.modules.framework.user.model.User;

public interface GoalRepository extends JpaRepository<Goal, UUID> {
  @Query(
      """
      SELECT g FROM Goal g
      WHERE g.user = :user
      AND g.status = :status
  """)
  List<Goal> findAllPendingByUser(User user, GoalStatusType status);

  Boolean existsByUserAndStatus(User user, GoalStatusType status);

  @Modifying
  @Query("DELETE FROM Goal g WHERE g.user = :user AND g.status = :status")
  void deleteByUserAndStatus(User user, GoalStatusType status);

  @Modifying
  @Query(
      """
    UPDATE Goal g
    SET g.status = 'EXPIRED'
    WHERE g.status = 'PENDING'
  """)
  void expirePendingGoals();
}
