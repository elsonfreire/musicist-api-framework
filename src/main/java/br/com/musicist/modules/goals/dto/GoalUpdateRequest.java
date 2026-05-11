package br.com.musicist.modules.goals.dto;

import br.com.musicist.modules.goals.enums.GoalStatusType;

public record GoalUpdateRequest(
    GoalStatusType status
) {}
