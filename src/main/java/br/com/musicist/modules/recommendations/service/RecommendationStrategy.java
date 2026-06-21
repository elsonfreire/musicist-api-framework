package br.com.musicist.modules.recommendations.service;

import br.com.musicist.modules.user.model.User;

public interface RecommendationStrategy {
    
    int calculateScore(User me, User other);
}
