package br.com.musicist.modules.goals.controller;

import br.com.musicist.modules.goals.service.GeminiService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import br.com.musicist.modules.goals.dto.GoalResponse;
import br.com.musicist.modules.goals.service.GoalService;
import br.com.musicist.modules.user.model.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;


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
}
