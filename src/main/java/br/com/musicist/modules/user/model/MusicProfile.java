package br.com.musicist.modules.user.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import br.com.musicist.common.enums.InstrumentType;
import br.com.musicist.common.enums.InterestType;
import br.com.musicist.common.enums.LevelType;
import br.com.musicist.common.enums.MusicGenreType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    @UuidGenerator(style = UuidGenerator.Style.VERSION_7)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
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
