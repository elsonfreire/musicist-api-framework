package br.com.habit.modules.readist.profile;

import java.util.ArrayList;
import java.util.List;

import br.com.habit.modules.framework.user.model.DomainProfile;
import br.com.habit.modules.readist.enums.BookFormatType;
import br.com.habit.modules.readist.enums.BookGenreType;
import br.com.habit.modules.readist.enums.InterestType;
import br.com.habit.modules.readist.enums.LevelType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "read_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadProfile extends DomainProfile {
    
    @Enumerated(EnumType.STRING)
    private BookFormatType preferredFormat;

    @Enumerated(EnumType.STRING)
    private LevelType level;

    @Enumerated(EnumType.STRING)
    private BookGenreType favoriteGenre;

    @ElementCollection(targetClass = InterestType.class)
    @CollectionTable(
            name = "read_profile_interests",
            joinColumns = @JoinColumn(name = "read_profile_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "interest")
    private List<InterestType> interests = new ArrayList<>();
}