package br.com.musicist.modules.goals.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.musicist.modules.goals.service.GoalService;

@Component
public class GoalScheduler {
    @Autowired
    private GoalService goalService;

    @Scheduled(cron = "0 0 0 * * MON")
    public void deleteWeeklyGoals() {
        goalService.expireAllPendingGoals();
    }
}
