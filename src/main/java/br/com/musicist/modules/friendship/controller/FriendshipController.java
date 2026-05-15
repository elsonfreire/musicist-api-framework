package br.com.musicist.modules.friendship.controller;

import br.com.musicist.modules.friendship.dto.FriendshipResponse;
import br.com.musicist.modules.friendship.service.FriendshipService;
import br.com.musicist.modules.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{id}/friends")
@RequiredArgsConstructor
public class FriendshipController {
  private final FriendshipService friendshipService;

  @GetMapping
  public ResponseEntity<List<UserResponse>> getFriends(@PathVariable Long id) {
    return ResponseEntity.ok(friendshipService.getFriends(id));
  }

  @GetMapping("/requests")
  public ResponseEntity<List<FriendshipResponse>> getPendingRequests(@PathVariable Long id) {
    return ResponseEntity.ok(friendshipService.getPendingRequests(id));
  }

  @PostMapping("/requests/{targetId}")
  public ResponseEntity<Void> sendRequest(@PathVariable Long id, @PathVariable Long targetId) {
    friendshipService.sendRequest(id, targetId);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PatchMapping("/requests/{friendshipId}/accept")
  public ResponseEntity<Void> acceptRequest(
      @PathVariable Long id, @PathVariable Long friendshipId) {
    friendshipService.acceptRequest(friendshipId, id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{friendId}")
  public ResponseEntity<Void> removeFriend(@PathVariable Long id, @PathVariable Long friendId) {
    friendshipService.removeFriend(id, friendId);
    return ResponseEntity.noContent().build();
  }
}
