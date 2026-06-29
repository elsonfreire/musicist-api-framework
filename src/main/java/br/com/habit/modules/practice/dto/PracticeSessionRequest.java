package br.com.habit.modules.practice.dto;

import java.time.LocalDate;

import br.com.habit.modules.musicist.enums.InstrumentType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PracticeSessionRequest(
    @NotNull(message = "Duration is required")
        @Min(value = 1, message = "Duration should be at least 1 minute")
        @Max(value = 1440, message = "Duration cannot exceed 1440 minutes (24h)")
        Integer durationMinutes,
    @NotNull(message = "Instrument is required") InstrumentType instrument,
    @Size(max = 400, message = "Notes should have a maximum of 400 characters") String notes,
    @NotNull(message = "Date is required") LocalDate date) {}
