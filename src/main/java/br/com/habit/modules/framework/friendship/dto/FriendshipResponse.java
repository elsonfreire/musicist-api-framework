package br.com.habit.modules.framework.friendship.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.habit.modules.framework.friendship.enums.FriendshipStatusType;
import br.com.habit.modules.framework.friendship.model.Friendship;
import br.com.habit.modules.framework.user.dto.UserResponse;

public record FriendshipResponse(
    UUID id,
    UserResponse requester,
    UserResponse receiver,
    FriendshipStatusType status,
    LocalDateTime createdAt) {
  public static FriendshipResponse from(Friendship f) {
    return new FriendshipResponse(
        f.getId(),
        UserResponse.from(f.getRequester()),
        UserResponse.from(f.getReceiver()),
        f.getStatus(),
        f.getCreatedAt());
  }
}
