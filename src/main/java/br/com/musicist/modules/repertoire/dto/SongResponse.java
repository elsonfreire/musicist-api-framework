package br.com.musicist.modules.repertoire.dto;

import br.com.musicist.modules.repertoire.enums.DifficultyType;
import br.com.musicist.modules.repertoire.model.Song;

public record SongResponse (
    Long id,
    String title,
    String artist,
    DifficultyType difficulty
) {
    public SongResponse(Song song) {
        this(song.getId(), song.getTitle(), song.getArtist(), song.getDifficulty());
    }
}
