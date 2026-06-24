package br.com.musicist.modules.framework.goals.dto;

import java.util.UUID;

import br.com.musicist.modules.framework.goals.model.Goal;

public record GoalResponse(UUID id, String title) {
  public GoalResponse(Goal goal) {
    this(goal.getId(), goal.getTitle());
  }
}
