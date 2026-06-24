package br.com.musicist.modules.framework.goals.dto;

import br.com.musicist.modules.framework.goals.enums.GoalStatusType;

public record GoalUpdateRequest(GoalStatusType status) {}
