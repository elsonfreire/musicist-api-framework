package br.com.habit.modules.framework.user.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public record UserResponse(
        UUID id,
        String username,
        String email,
        String bio,
        String city,
        String state,
        LocalDateTime createdAt,
        @JsonUnwrapped  Object domainProfileData) {
}
