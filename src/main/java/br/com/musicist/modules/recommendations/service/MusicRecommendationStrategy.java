package br.com.musicist.modules.recommendations.service;

import org.springframework.stereotype.Component;

import br.com.musicist.modules.user.model.MusicProfile;
import br.com.musicist.modules.user.model.User;

@Component
public class MusicRecommendationStrategy implements RecommendationStrategy {
    
  @Override
  public int calculateScore(User me, User other) {
    int score = 0;

    if (me.getCity() != null && me.getCity().equalsIgnoreCase(other.getCity())) {
      score += 5;
    } else if (me.getState() != null && me.getState().equalsIgnoreCase(other.getState())) {
      score += 3;
    }

    MusicProfile musicProfile = me.getMusicProfile();
    MusicProfile otherMusicProfile = other.getMusicProfile();

    if (musicProfile.getInterests() != null
        && !musicProfile.getInterests().isEmpty()
        && otherMusicProfile.getInterests() != null
        && !otherMusicProfile.getInterests().isEmpty()) {

      long common = musicProfile.getInterests().stream().filter(otherMusicProfile.getInterests()::contains).count();

      int minInterests = Math.min(musicProfile.getInterests().size(), otherMusicProfile.getInterests().size());
      double matchPercentage = (double) common / minInterests;
      score += (int) Math.round(matchPercentage * 3);
    }

    if (musicProfile.getFavoriteGenre() != null && musicProfile.getFavoriteGenre() == otherMusicProfile.getFavoriteGenre()) {
      score += 2;
    }

    return score;
  }
}
