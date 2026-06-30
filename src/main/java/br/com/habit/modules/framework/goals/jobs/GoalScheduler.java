package br.com.habit.modules.framework.goals.jobs;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.habit.modules.framework.goals.service.GoalService;

@Component
@RequiredArgsConstructor
public class GoalScheduler {
  private final GoalService goalService;

  @Scheduled(cron = "0 0 0 * * MON")
  public void deleteWeeklyGoals() {
    goalService.expireAllPendingGoals();
  }
}
