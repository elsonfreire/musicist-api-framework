package br.com.musicist.modules.friendship.dto;

import br.com.musicist.modules.friendship.enums.FriendshipStatusType;
import br.com.musicist.modules.friendship.model.Friendship;
import br.com.musicist.modules.user.dto.UserResponse;

import java.time.LocalDateTime;

public record FriendshipResponse(
    Long id,
    UserResponse requester,
    UserResponse receiver,
    FriendshipStatusType status,
    LocalDateTime createdAt) {
  public static FriendshipResponse from(Friendship f) {
    return new FriendshipResponse(
        f.getId(),
        new UserResponse(f.getRequester()),
        new UserResponse(f.getReceiver()),
        f.getStatus(),
        f.getCreatedAt());
  }
}
