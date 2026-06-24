package br.com.habit.modules.framework.recommendations.service;

import br.com.habit.modules.framework.user.model.User;

public interface RecommendationStrategy {
    
    int calculateScore(User me, User other);
}
