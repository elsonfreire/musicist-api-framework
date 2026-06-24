package br.com.musicist.modules.framework.forum.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.musicist.modules.framework.forum.enums.ForumCategoryType;
import br.com.musicist.modules.framework.forum.model.Topic;
import br.com.musicist.modules.framework.user.dto.UserSimplifiedResponse;

public record TopicResponse(
    UUID id,
    String title,
    ForumCategoryType category,
    String description,
    UserSimplifiedResponse user,
    LocalDateTime createdAt) {
  public TopicResponse(Topic topic) {
    this(
        topic.getId(),
        topic.getTitle(),
        topic.getCategory(),
        topic.getDescription(),
        new UserSimplifiedResponse(topic.getUser()),
        topic.getCreatedAt());
  }
}
