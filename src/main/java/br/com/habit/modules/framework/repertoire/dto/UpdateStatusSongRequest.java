package br.com.habit.modules.framework.repertoire.dto;

import br.com.habit.modules.framework.repertoire.enums.LearningStatusType;
import jakarta.validation.constraints.NotNull;

public record UpdateStatusSongRequest(
    @NotNull(message = "Status is required") LearningStatusType status) {}
