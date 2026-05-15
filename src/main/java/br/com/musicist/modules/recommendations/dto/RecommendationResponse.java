package br.com.musicist.modules.recommendations.dto;

import br.com.musicist.modules.user.dto.UserResponse;

public record RecommendationResponse(UserResponse user, Integer matchScore) {}
