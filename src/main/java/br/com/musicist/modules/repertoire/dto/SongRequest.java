package br.com.musicist.modules.repertoire.dto;

import br.com.musicist.modules.repertoire.enums.DifficultyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SongRequest(
    @NotBlank(message = "Title of the song is required") String title,
    @NotBlank(message = "Artist of the song is required") String artist,
    @NotNull(message = "Difficulty is required") DifficultyType difficulty) {}
