package br.com.musicist.modules.forum.dto;

import java.time.LocalDateTime;

import br.com.musicist.modules.forum.enums.ForumCategoryType;
import br.com.musicist.modules.forum.model.Topic;
import br.com.musicist.modules.user.dto.UserSimplifiedResponse;

public record TopicResponse(
    Long id,
    String title,
    ForumCategoryType category,
    String description,
    UserSimplifiedResponse user,
    LocalDateTime createdAt
) {
    public TopicResponse(Topic topic) {
        this(topic.getId(), topic.getTitle(),topic.getCategory(), topic.getDescription(), new UserSimplifiedResponse(topic.getUser()), topic.getCreatedAt());
    }
}
