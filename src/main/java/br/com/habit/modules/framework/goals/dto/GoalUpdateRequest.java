package br.com.habit.modules.framework.goals.dto;

import br.com.habit.modules.framework.goals.enums.GoalStatusType;

public record GoalUpdateRequest(GoalStatusType status) {}
