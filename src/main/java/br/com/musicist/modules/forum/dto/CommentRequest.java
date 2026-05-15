package br.com.musicist.modules.forum.dto;

import java.time.LocalDateTime;

import br.com.musicist.modules.user.dto.UserResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentRequest(
    Long id,
    @NotBlank(message = "Content is required")
        @Size(max = 400, message = "Content should have a maximum of 400 characters")
        String content,
    UserResponse author,
    LocalDateTime createdAt) {}
