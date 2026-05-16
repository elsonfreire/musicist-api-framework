package br.com.musicist.modules.user.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.musicist.common.enums.InstrumentType;
import br.com.musicist.common.enums.InterestType;
import br.com.musicist.common.enums.LevelType;
import br.com.musicist.common.enums.MusicGenreType;
import br.com.musicist.modules.practice.model.PracticeSession;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
  @Id
  @UuidGenerator(style = UuidGenerator.Style.VERSION_7)
  private UUID id;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  @JsonIgnore
  private String passwordHash;

  @Enumerated(EnumType.STRING)
  private InstrumentType instrument;

  private String bio;

  @Enumerated(EnumType.STRING)
  private LevelType level;

  private String city;

  private String state;

  @Enumerated(EnumType.STRING)
  private MusicGenreType favoriteGenre;

  @ElementCollection(targetClass = InterestType.class)
  @CollectionTable(name = "user_interests", joinColumns = @JoinColumn(name = "user_id"))
  @Enumerated(EnumType.STRING)
  @Column(name = "interest")
  private List<InterestType> interests = new ArrayList<>();

  private Integer currentStreak = 0;

  private Integer longestStreak = 0;

  private LocalDateTime lastPracticeDate;

  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PracticeSession> practiceSessions = new ArrayList<>();

  @PrePersist
  public void prePersist() {
    this.createdAt = LocalDateTime.now();
  }

  public User(String email, String username, String passwordHash) {
    this.email = email;
    this.username = username;
    this.passwordHash = passwordHash;
  }
}
