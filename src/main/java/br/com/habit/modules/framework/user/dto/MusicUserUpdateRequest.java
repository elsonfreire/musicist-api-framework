package br.com.habit.modules.framework.user.dto;

import java.util.List;

import br.com.habit.modules.musicist.enums.InstrumentType;
import br.com.habit.modules.musicist.enums.InterestType;
import br.com.habit.modules.musicist.enums.LevelType;
import br.com.habit.modules.musicist.enums.MusicGenreType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicUserUpdateRequest extends UserUpdateRequest {
    InstrumentType instrument;
    LevelType level;
    MusicGenreType favoriteGenre;
    List<InterestType> interests;
}
