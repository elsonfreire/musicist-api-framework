package br.com.habit.modules.framework.practice.service;

import br.com.habit.modules.framework.practice.dto.PracticeRequest;
import br.com.habit.modules.framework.practice.model.Practice;
import br.com.habit.modules.framework.user.model.User;

public interface PracticeStrategy {
    
    Practice createPracticeEntity(PracticeRequest request, User user);
}