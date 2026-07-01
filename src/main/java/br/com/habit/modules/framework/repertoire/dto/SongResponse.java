package br.com.habit.modules.framework.repertoire.dto;

import br.com.habit.modules.framework.repertoire.enums.DifficultyType;
import br.com.habit.modules.framework.repertoire.model.Song;

import java.util.UUID;

public record SongResponse(UUID id, String title, String artist, DifficultyType difficulty) {
  public SongResponse(Song song) {
    this(song.getId(), song.getTitle(), song.getArtist(), song.getDifficulty());
  }
}
