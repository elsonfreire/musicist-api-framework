package br.com.musicist.modules.friendship.service;

import br.com.musicist.modules.friendship.dto.FriendshipResponse;
import br.com.musicist.modules.friendship.enums.FriendshipStatusType;
import br.com.musicist.modules.friendship.exceptions.FriendshipAlreadyExistsException;
import br.com.musicist.modules.friendship.exceptions.FriendshipNotFoundException;
import br.com.musicist.modules.friendship.exceptions.FriendshipUnauthorizedException;
import br.com.musicist.modules.friendship.model.Friendship;
import br.com.musicist.modules.friendship.repository.FriendshipRepository;
import br.com.musicist.modules.user.dto.UserResponse;
import br.com.musicist.modules.user.model.User;
import br.com.musicist.modules.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendshipService {
  private final FriendshipRepository friendshipRepository;

  private final UserRepository userRepository;

  public void sendRequest(Long requesterId, Long receiverId) {
    if (requesterId.equals(receiverId)) throw new IllegalArgumentException("Cannot add yourself");

    friendshipRepository
        .findBetween(requesterId, receiverId)
        .ifPresent(
            f -> {
              throw new FriendshipAlreadyExistsException(f.getStatus());
            });

    User requester = userRepository.findById(requesterId).orElseThrow();
    User addressee = userRepository.findById(receiverId).orElseThrow();

    Friendship friendship = new Friendship();
    friendship.setRequester(requester);
    friendship.setReceiver(addressee);
    friendshipRepository.save(friendship);
  }

  public void acceptRequest(Long friendshipId, Long currentUserId) {
    Friendship friendship = friendshipRepository.findById(friendshipId).orElseThrow();

    if (!friendship.getReceiver().getId().equals(currentUserId))
      throw new FriendshipUnauthorizedException();

    friendship.setStatus(FriendshipStatusType.ACCEPTED);
  }

  public void removeFriend(Long userId, Long friendId) {
    Friendship friendship =
        friendshipRepository
            .findBetween(userId, friendId)
            .orElseThrow(FriendshipNotFoundException::new);
    friendshipRepository.delete(friendship);
  }

  public List<UserResponse> getFriends(Long userId) {
    return friendshipRepository.findAcceptedFriendships(userId).stream()
        .map(f -> f.getRequester().getId().equals(userId) ? f.getReceiver() : f.getRequester())
        .map(UserResponse::new)
        .toList();
  }

  public List<FriendshipResponse> getPendingRequests(Long userId) {
    return friendshipRepository
        .findByReceiverIdAndStatus(userId, FriendshipStatusType.PENDING)
        .stream()
        .map(FriendshipResponse::from)
        .toList();
  }
}
