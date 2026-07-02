package br.com.habit.modules.framework.repertoire.dto;

import br.com.habit.modules.framework.repertoire.enums.DifficultyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SongRequest(
    @NotBlank(message = "Title of the song is required") String title,
    @NotBlank(message = "Artist of the song is required") String artist,
    @NotNull(message = "Difficulty is required") DifficultyType difficulty) {}
