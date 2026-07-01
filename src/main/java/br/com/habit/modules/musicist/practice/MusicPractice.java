package br.com.habit.modules.musicist.practice;

import br.com.habit.modules.framework.practice.model.Practice;
import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.musicist.enums.InstrumentType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "music_practice_sessions")
@Getter
@Setter
@NoArgsConstructor
public class MusicPractice extends Practice {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InstrumentType instrument;

    public MusicPractice(Integer durationMinutes, String notes, LocalDate date, User user, InstrumentType instrument) {
        super(durationMinutes, notes, date, user);
        this.instrument = instrument;
    }
}