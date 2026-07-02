package br.com.habit.modules.framework.user_collection.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateStatusRequest<TStatus> (
    @NotNull TStatus status
) {
}
