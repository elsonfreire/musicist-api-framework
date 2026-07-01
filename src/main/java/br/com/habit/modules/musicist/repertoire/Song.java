package br.com.habit.modules.musicist.repertoire;

import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.framework.user_collection.model.UserCollectionItem;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "songs")
public class Song extends UserCollectionItem {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LearningStatusType status;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DifficultyType difficulty;

    public Song(
            User user,
            LearningStatusType status,
            String title,
            String artist,
            DifficultyType difficulty) {
        this.user = user;
        this.status = status;
        this.title = title;
        this.artist = artist;
        this.difficulty = difficulty;
    }
}
