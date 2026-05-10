package br.com.musicist.modules.goals.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.musicist.modules.goals.dto.GoalResponse;
import br.com.musicist.modules.goals.enums.GoalStatusType;
import br.com.musicist.modules.goals.exceptions.ActiveGoalsException;
import br.com.musicist.modules.goals.model.Goal;
import br.com.musicist.modules.goals.dto.GoalUpdateRequest;
import br.com.musicist.modules.goals.exceptions.GoalNotFoundException;
import br.com.musicist.modules.goals.exceptions.InvalidGoalStatusTransition;
import br.com.musicist.modules.goals.exceptions.GoalAlreadyResolvedException;
import br.com.musicist.modules.goals.repository.GoalRepository;
import br.com.musicist.modules.user.model.User;

@Service
public class GoalService {
    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private GeminiService geminiService;
    
    public List<GoalResponse> findPendingByUserOrGenerate(User user) { 
        List<Goal> pendingGoals = goalRepository.findAllPendingByUser(user, GoalStatusType.PENDING);
        
        if (pendingGoals.isEmpty()) {
            return generateGoals(user);
        }

        return pendingGoals.stream()
            .map(goal -> new GoalResponse(goal))
            .collect(Collectors.toList());
    }

    public GoalResponse update(Long id, GoalUpdateRequest goalUpdateRequest) {
        Goal goal = goalRepository.findById(id)
            .orElseThrow(() -> new GoalNotFoundException());

        if (goalUpdateRequest.status() != null) 
            updateGoalStatus(goal, goalUpdateRequest.status());

        Goal newGoal = goalRepository.save(goal);

        return new GoalResponse(newGoal);
    }

    private void updateGoalStatus(Goal goal, GoalStatusType newStatus) {
        if (newStatus == GoalStatusType.PENDING || newStatus == goal.getStatus()) {
            throw new InvalidGoalStatusTransition();
        }
        if (goal.getStatus() != GoalStatusType.PENDING) {
            throw new GoalAlreadyResolvedException();
        }
        goal.setStatus(newStatus);

    }
    
    public List<GoalResponse> generateGoals(User user) {
        boolean hasActivePendingGoals = goalRepository
                .existsByUserAndStatus(user, GoalStatusType.PENDING);

        if (hasActivePendingGoals) {
            throw new ActiveGoalsException();
        }

        return generateAndSave(user).stream().map(GoalResponse::new).toList();
    }

    public List<Goal> generateAndSave(User user) {
        List<Goal> goals = geminiService.getSuggestGoals(user).stream()
                .map(title -> {
                    Goal goal = new Goal();
                    goal.setUser(user);
                    goal.setTitle(title);
                    return goal;
                })
                .toList();

        return goalRepository.saveAll(goals);
    }

    @Transactional
    public void expireAllPendingGoals() {
        goalRepository.expirePendingGoals();
    }
}
