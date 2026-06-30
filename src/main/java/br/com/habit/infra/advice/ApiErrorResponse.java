package br.com.habit.infra.advice;

import java.time.LocalDateTime;

public record ApiErrorResponse(
    int status, String message, String path, Object details, LocalDateTime timestamp) {}
