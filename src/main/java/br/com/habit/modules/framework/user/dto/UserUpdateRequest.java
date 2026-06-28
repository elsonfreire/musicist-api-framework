package br.com.habit.modules.framework.user.dto;

import java.util.List;

import br.com.habit.modules.musicist.enums.InstrumentType;
import br.com.habit.modules.musicist.enums.InterestType;
import br.com.habit.modules.musicist.enums.LevelType;
import br.com.habit.modules.musicist.enums.MusicGenreType;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest (
    @Size(min = 3, max = 50, message = "Username should be between 3 and 50 characters")
        String username,
    @Size(max = 400, message = "Bio should have a maximum of 400 characters") String bio,
    @Size(max = 100, message = "City must have a maximum of 100 characters") String city,
    @Size(max = 100, message = "State must have a maximum of 100 characters") String state,
    InstrumentType instrument,
    LevelType level,
    MusicGenreType favoriteGenre,
    List<InterestType> interests
) {}
