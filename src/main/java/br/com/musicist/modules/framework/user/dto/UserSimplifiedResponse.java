package br.com.musicist.modules.framework.user.dto;

import java.util.UUID;

import br.com.musicist.modules.framework.user.model.User;

public record UserSimplifiedResponse(UUID id, String username) {
  public UserSimplifiedResponse(User user) {
    this(user.getId(), user.getUsername());
  }
}
