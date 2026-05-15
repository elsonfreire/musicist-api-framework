package br.com.musicist.modules.forum.dto;

import java.time.LocalDateTime;

import br.com.musicist.modules.forum.model.Comment;
import br.com.musicist.modules.user.dto.UserSimplifiedResponse;

public record CommentResponse(
    Long id, String content, UserSimplifiedResponse author, Long topicId, LocalDateTime createdAt) {
  public CommentResponse(Comment comment) {
    this(
        comment.getId(),
        comment.getContent(),
        new UserSimplifiedResponse(comment.getAuthor()),
        comment.getTopic().getId(),
        comment.getCreatedAt());
  }
}
