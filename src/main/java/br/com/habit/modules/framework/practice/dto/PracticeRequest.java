package br.com.habit.modules.framework.practice.dto;

import java.time.LocalDate;
import java.util.Map;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PracticeRequest(
    @NotNull(message = "Domain type is required (e.g., MUSIC)") 
    String domain,

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration should be at least 1 minute")
    @Max(value = 1440, message = "Duration cannot exceed 1440 minutes (24h)")
    Integer durationMinutes,

    @Size(max = 400, message = "Notes should have a maximum of 400 characters") 
    String notes,

    @NotNull(message = "Date is required") 
    LocalDate date,

    Map<String, Object> domainData
) {}