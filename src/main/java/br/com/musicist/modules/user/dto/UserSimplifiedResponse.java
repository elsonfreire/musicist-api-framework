package br.com.musicist.modules.user.dto;

import br.com.musicist.modules.user.model.User;

public record UserSimplifiedResponse(Long id, String username) {
  public UserSimplifiedResponse(User user) {
    this(user.getId(), user.getUsername());
  }
}
