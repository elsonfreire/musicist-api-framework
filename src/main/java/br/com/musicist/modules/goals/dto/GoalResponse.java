package br.com.musicist.modules.goals.dto;

import br.com.musicist.modules.goals.model.Goal;

public record GoalResponse(Long id, String title) {
  public GoalResponse(Goal goal) {
    this(goal.getId(), goal.getTitle());
  }
}
