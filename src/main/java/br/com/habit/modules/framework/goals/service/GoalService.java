package br.com.habit.modules.framework.goals.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.habit.modules.framework.goals.dto.GoalResponse;
import br.com.habit.modules.framework.goals.dto.GoalUpdateRequest;
import br.com.habit.modules.framework.goals.enums.GoalStatusType;
import br.com.habit.modules.framework.goals.exceptions.ActiveGoalsException;
import br.com.habit.modules.framework.goals.exceptions.GoalAlreadyResolvedException;
import br.com.habit.modules.framework.goals.exceptions.GoalNotFoundException;
import br.com.habit.modules.framework.goals.exceptions.InvalidGoalStatusTransition;
import br.com.habit.modules.framework.goals.model.Goal;
import br.com.habit.modules.framework.goals.repository.GoalRepository;
import br.com.habit.modules.framework.user.model.User;

@Service
@RequiredArgsConstructor
public class GoalService {
  private final GoalRepository goalRepository;

  private final GoalSuggestionTemplate goalSuggestion;

  public List<GoalResponse> findPendingByUserOrGenerate(User user) {
    List<Goal> pendingGoals = goalRepository.findAllPendingByUser(user, GoalStatusType.PENDING);

    if (pendingGoals.isEmpty()) {
      return generateGoals(user);
    }

    return pendingGoals.stream().map(GoalResponse::new).collect(Collectors.toList());
  }

  public GoalResponse update(UUID id, GoalUpdateRequest goalUpdateRequest) {
    Goal goal = goalRepository.findById(id).orElseThrow(GoalNotFoundException::new);

    if (goalUpdateRequest.status() != null) updateGoalStatus(goal, goalUpdateRequest.status());

    Goal newGoal = goalRepository.save(goal);

    return new GoalResponse(newGoal);
  }

  private void updateGoalStatus(Goal goal, GoalStatusType newStatus) {
    if (newStatus == GoalStatusType.PENDING || newStatus == goal.getStatus()) {
      throw new InvalidGoalStatusTransition();
    }
    if (goal.getStatus() != GoalStatusType.PENDING) {
      throw new GoalAlreadyResolvedException();
    }
    goal.updateStatus(newStatus);
  }

  private List<GoalResponse> generateGoals(User user) {
    boolean hasActivePendingGoals =
        goalRepository.existsByUserAndStatus(user, GoalStatusType.PENDING);

    if (hasActivePendingGoals) {
      throw new ActiveGoalsException();
    }

    return generateAndSave(user).stream().map(GoalResponse::new).toList();
  }

  public List<Goal> generateAndSave(User user) {
    List<Goal> goals =
        goalSuggestion.suggestGoals(user).stream()
            .map(title -> new Goal(user, title))
            .toList();

    return goalRepository.saveAll(goals);
  }

  @Transactional
  public void expireAllPendingGoals() {
    goalRepository.expirePendingGoals();
  }
}
