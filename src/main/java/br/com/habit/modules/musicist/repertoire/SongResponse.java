package br.com.habit.modules.musicist.repertoire;

import java.util.UUID;

import br.com.habit.modules.user_collection.model.Song;

public record SongResponse(UUID id, String title, String artist, DifficultyType difficulty) {
  public SongResponse(Song song) {
    this(song.getId(), song.getTitle(), song.getArtist(), song.getDifficulty());
  }
}
