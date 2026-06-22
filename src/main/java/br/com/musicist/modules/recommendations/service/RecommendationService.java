package br.com.musicist.modules.recommendations.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import br.com.musicist.modules.user.exceptions.AuthenticatedUserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.musicist.modules.friendship.repository.FriendshipRepository;
import br.com.musicist.modules.recommendations.dto.RecommendationResponse;
import br.com.musicist.modules.user.dto.UserResponse;
import br.com.musicist.modules.user.model.User;
import br.com.musicist.modules.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class RecommendationService {

  private final UserRepository userRepository;

  private final FriendshipRepository friendshipRepository;

  private final RecommendationStrategy recommendationStrategy;

  @Transactional(readOnly = true)
  public List<RecommendationResponse> getRecommendations(User authenticatedUser) {
    User currentUser =
        userRepository
            .findById(authenticatedUser.getId())
            .orElseThrow(AuthenticatedUserNotFoundException::new);

    List<UUID> connectedIds = friendshipRepository.findConnectedUserIds(currentUser.getId());

    List<UUID> idsToExclude = new ArrayList<>(connectedIds);
    idsToExclude.add(currentUser.getId());

    return userRepository.findByIdNotIn(idsToExclude).stream()
        .map(
            candidate -> {
              int score = recommendationStrategy.calculateScore(currentUser, candidate);
              return new RecommendationResponse(new UserResponse(candidate), score);
            })
        .filter(rec -> rec.matchScore() > 0)
        .sorted(Comparator.comparing(RecommendationResponse::matchScore).reversed())
        .limit(10)
        .toList();
  }
}
