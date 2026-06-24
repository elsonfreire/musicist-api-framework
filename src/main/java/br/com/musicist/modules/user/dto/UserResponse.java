package br.com.musicist.modules.user.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.musicist.common.enums.InstrumentType;
import br.com.musicist.common.enums.InterestType;
import br.com.musicist.common.enums.LevelType;
import br.com.musicist.common.enums.MusicGenreType;
import br.com.musicist.modules.user.model.User;

public record UserResponse(
    UUID id,
    String username,
    String email,
    String bio,
    LevelType level,
    InstrumentType instrument,
    String city,
    String state,
    MusicGenreType favoriteGenre,
    List<InterestType> interests,
    LocalDateTime createdAt) {
  public UserResponse(User user) {
    this(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getBio(),
        user.getMusicProfile().getLevel(),
        user.getMusicProfile().getInstrument(),
        user.getCity(),
        user.getState(),
        user.getMusicProfile().getFavoriteGenre(),
        user.getMusicProfile().getInterests(),
        user.getCreatedAt()
      );
  }
}
