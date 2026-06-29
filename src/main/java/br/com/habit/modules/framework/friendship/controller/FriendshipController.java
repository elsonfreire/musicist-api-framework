package br.com.habit.modules.framework.friendship.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.habit.modules.framework.friendship.dto.FriendshipResponse;
import br.com.habit.modules.framework.friendship.service.FriendshipService;
import br.com.habit.modules.framework.user.dto.UserResponse;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/{id}/friends")
@RequiredArgsConstructor
public class FriendshipController {
  private final FriendshipService friendshipService;

  @GetMapping
  public ResponseEntity<List<UserResponse>> getFriends(@PathVariable UUID id) {
    return ResponseEntity.ok(friendshipService.getFriends(id));
  }

  @GetMapping("/requests")
  public ResponseEntity<List<FriendshipResponse>> getPendingRequests(@PathVariable UUID id) {
    return ResponseEntity.ok(friendshipService.getPendingRequests(id));
  }

  @PostMapping("/requests/{targetId}")
  public ResponseEntity<Void> sendRequest(@PathVariable UUID id, @PathVariable UUID targetId) {
    friendshipService.sendRequest(id, targetId);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PatchMapping("/requests/{friendshipId}/accept")
  public ResponseEntity<Void> acceptRequest(
      @PathVariable UUID id, @PathVariable UUID friendshipId) {
    friendshipService.acceptRequest(friendshipId, id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{friendId}")
  public ResponseEntity<Void> removeFriend(@PathVariable UUID id, @PathVariable UUID friendId) {
    friendshipService.removeFriend(id, friendId);
    return ResponseEntity.noContent().build();
  }
}
