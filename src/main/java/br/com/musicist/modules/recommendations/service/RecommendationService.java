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
              int score = calculateScore(currentUser, candidate);
              return new RecommendationResponse(new UserResponse(candidate), score);
            })
        .filter(rec -> rec.matchScore() > 0)
        .sorted(Comparator.comparing(RecommendationResponse::matchScore).reversed())
        .limit(10)
        .toList();
  }

  private int calculateScore(User me, User other) {
    int score = 0;

    if (me.getCity() != null && me.getCity().equalsIgnoreCase(other.getCity())) {
      score += 5;
    } else if (me.getState() != null && me.getState().equalsIgnoreCase(other.getState())) {
      score += 3;
    }

    if (me.getInterests() != null
        && !me.getInterests().isEmpty()
        && other.getInterests() != null
        && !other.getInterests().isEmpty()) {

      long common = me.getInterests().stream().filter(other.getInterests()::contains).count();

      int minInterests = Math.min(me.getInterests().size(), other.getInterests().size());
      double matchPercentage = (double) common / minInterests;
      score += (int) Math.round(matchPercentage * 3);
    }

    if (me.getFavoriteGenre() != null && me.getFavoriteGenre() == other.getFavoriteGenre()) {
      score += 2;
    }

    return score;
  }
}
