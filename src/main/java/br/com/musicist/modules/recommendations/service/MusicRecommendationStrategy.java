package br.com.musicist.modules.recommendations.service;

import org.springframework.stereotype.Component;

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
