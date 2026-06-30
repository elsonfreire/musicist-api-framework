package br.com.habit.modules.framework.goals.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.habit.modules.framework.goals.dto.GoalResponse;
import br.com.habit.modules.framework.goals.dto.GoalUpdateRequest;
import br.com.habit.modules.framework.goals.service.GoalService;
import br.com.habit.modules.framework.user.model.User;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/goals")
@RequiredArgsConstructor
public class GoalController {
  private final GoalService goalService;

  @GetMapping
  public ResponseEntity<List<GoalResponse>> getAllMyPendingGoals(
      @AuthenticationPrincipal User currentUser) {
    return ResponseEntity.ok(goalService.findPendingByUserOrGenerate(currentUser));
  }

  @PutMapping("/{id}")
  public ResponseEntity<GoalResponse> updateGoal(
      @PathVariable("id") UUID id, @RequestBody GoalUpdateRequest goalUpdateRequest) {
    return ResponseEntity.ok(goalService.update(id, goalUpdateRequest));
  }
}
