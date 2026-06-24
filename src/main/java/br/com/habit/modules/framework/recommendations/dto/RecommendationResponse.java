package br.com.habit.modules.framework.recommendations.dto;

import br.com.habit.modules.framework.user.dto.UserResponse;

public record RecommendationResponse(UserResponse user, Integer matchScore) {}
