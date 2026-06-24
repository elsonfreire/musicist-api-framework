package br.com.musicist.modules.framework.forum.dto;

import br.com.musicist.modules.framework.forum.enums.ForumCategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TopicRequest(
    @NotBlank(message = "Title is required")
        @Size(max = 100, message = "Title should have a maximum of 100 characters")
        String title,
    @NotNull(message = "Category is required") ForumCategoryType category,
    @NotBlank(message = "Description is required")
        @Size(max = 400, message = "Description should have a maximum of 400 characters")
        String description) {}
