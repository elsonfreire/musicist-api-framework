package br.com.habit.modules.framework.user.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.musicist.enums.InstrumentType;
import br.com.habit.modules.musicist.enums.InterestType;
import br.com.habit.modules.musicist.enums.LevelType;
import br.com.habit.modules.musicist.enums.MusicGenreType;
import br.com.habit.modules.musicist.profile.MusicProfile;

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
  public static UserResponse from(User user) {
    MusicProfile profile = (MusicProfile) user.getDomainProfile();
    
    return new UserResponse(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getBio(),
        profile.getLevel(),
        profile.getInstrument(),
        user.getCity(),
        user.getState(),
        profile.getFavoriteGenre(),
        profile.getInterests(),
        user.getCreatedAt()
      );
  }
}
