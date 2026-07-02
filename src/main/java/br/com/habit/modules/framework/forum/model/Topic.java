package br.com.habit.modules.framework.forum.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import br.com.habit.modules.framework.forum.enums.ForumCategoryType;
import br.com.habit.modules.framework.shared.model.UserOwnedEntity;
import br.com.habit.modules.framework.user.model.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topics")
public class Topic extends UserOwnedEntity {
  @Column(nullable = false)
  private String title;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ForumCategoryType category;

  @Column(nullable = false)
  private String description;

  @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments;

  public Topic(String title, ForumCategoryType category, String description, User user) {
    this.title = title;
    this.category = category;
    this.description = description;
    this.user = user;
  }
}
