package br.com.habit.modules.musicist.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.musicist.enums.InstrumentType;
import br.com.habit.modules.musicist.enums.InterestType;
import br.com.habit.modules.musicist.enums.LevelType;
import br.com.habit.modules.musicist.enums.MusicGenreType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "music_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MusicProfile {
    @Id
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Enumerated(EnumType.STRING)
    private InstrumentType instrument;

    @Enumerated(EnumType.STRING)
    private LevelType level;

    @Enumerated(EnumType.STRING)
    private MusicGenreType favoriteGenre;

    @ElementCollection(targetClass = InterestType.class)
    @CollectionTable(
            name = "music_profile_interests",
            joinColumns = @JoinColumn(name = "music_profile_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "interest")
    private List<InterestType> interests = new ArrayList<>();
}
