package br.com.musicist.modules.framework.recommendations.service;

import br.com.musicist.modules.framework.user.model.User;

public interface RecommendationStrategy {
    
    int calculateScore(User me, User other);
}
