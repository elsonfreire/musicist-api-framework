package br.com.musicist.modules.goals.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import br.com.musicist.modules.goals.enums.GoalStatusType;
import br.com.musicist.modules.goals.model.Goal;
import br.com.musicist.modules.user.model.User;


import br.com.musicist.modules.goals.enums.GoalStatusType;
import br.com.musicist.modules.goals.model.Goal;
import br.com.musicist.modules.user.model.User;

public interface GoalRepository extends JpaRepository<Goal, Long> {
  @Query(
    """
      SELECT g FROM Goal g
      WHERE g.user = :user
      AND g.status = "PENDING"
  """)
  List<Goal> findAllPendingByUser(User user);
  
  List<Goal> findByUserAndCreatedAtAfter(User user, LocalDate after);

  Boolean existsByUserAndStatus(User user, GoalStatusType status);

    @Modifying
    @Query("DELETE FROM Goal g WHERE g.user = :user AND g.status = :status")
    void deleteByUserAndStatus(User user, GoalStatusType status);
}
