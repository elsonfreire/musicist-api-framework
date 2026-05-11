package br.com.musicist.modules.goals.controller;

import br.com.musicist.modules.goals.service.GeminiService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import br.com.musicist.modules.goals.dto.GoalResponse;
import br.com.musicist.modules.goals.dto.GoalUpdateRequest;
import br.com.musicist.modules.goals.enums.GoalStatusType;
import br.com.musicist.modules.goals.service.GoalService;
import br.com.musicist.modules.user.model.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/goals")
public class GoalController {
    @Autowired
    private GoalService goalService;
    
    @GetMapping("/generate")
    public ResponseEntity<List<GoalResponse>> generateGoals(@AuthenticationPrincipal User currentUser) {
       return ResponseEntity.ok(goalService.generateGoals(currentUser));
    }

    @GetMapping
    public ResponseEntity<List<GoalResponse>> getAllMyPendingGoals(@AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(goalService.findAllPendingByUser(currentUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GoalResponse> updateGoal(@PathVariable("id") Long id, @RequestBody GoalUpdateRequest goalUpdateRequest) {
        return ResponseEntity.ok(goalService.update(id, goalUpdateRequest));
    }
}
