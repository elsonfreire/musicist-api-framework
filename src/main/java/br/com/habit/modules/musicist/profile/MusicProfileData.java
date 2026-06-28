package br.com.habit.modules.musicist.profile;

import java.util.List;

import br.com.habit.modules.musicist.enums.InstrumentType;
import br.com.habit.modules.musicist.enums.InterestType;
import br.com.habit.modules.musicist.enums.LevelType;
import br.com.habit.modules.musicist.enums.MusicGenreType;

public record MusicProfileData (
    InstrumentType instrument,
    LevelType level,
    MusicGenreType favoriteGenre,
    List<InterestType> interests
) {}
