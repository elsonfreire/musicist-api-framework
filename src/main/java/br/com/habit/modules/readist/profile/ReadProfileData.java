package br.com.habit.modules.readist.profile;

import java.util.List;

import br.com.habit.modules.readist.enums.BookFormatType;
import br.com.habit.modules.readist.enums.BookGenreType;
import br.com.habit.modules.readist.enums.InterestType;
import br.com.habit.modules.readist.enums.LevelType;

public record ReadProfileData (
    BookFormatType preferredFormat,
    LevelType level,
    BookGenreType favoriteGenre,
    List<InterestType> interests
) {}