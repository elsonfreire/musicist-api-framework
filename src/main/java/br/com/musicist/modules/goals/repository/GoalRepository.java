package br.com.musicist.modules.goals.repository;

import br.com.musicist.modules.goals.enums.GoalStatusType;
import br.com.musicist.modules.goals.model.Goal;
import br.com.musicist.modules.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {
  List<Goal> findByUserAndCreatedAtAfter(User user, LocalDate after);

  @Query(
      """
        SELECT g FROM Goal g
        WHERE g.user = :user
        AND g.status = "PENDING"
    """)
  List<Goal> findAllPendingByUser(User user);

  Boolean existsByUserAndStatus(User user, GoalStatusType status);
}
