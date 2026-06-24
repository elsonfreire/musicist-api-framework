package br.com.musicist.modules.framework.recommendations.dto;

import br.com.musicist.modules.framework.user.dto.UserResponse;

public record RecommendationResponse(UserResponse user, Integer matchScore) {}
