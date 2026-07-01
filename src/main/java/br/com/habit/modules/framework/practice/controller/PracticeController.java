package br.com.habit.modules.framework.practice.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.habit.modules.framework.practice.dto.PracticeRequest;
import br.com.habit.modules.framework.practice.dto.PracticeResponse;
import br.com.habit.modules.framework.practice.service.PracticeService;
import br.com.habit.modules.framework.user.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/practices")
@RequiredArgsConstructor
public class PracticeController {

    private final PracticeService practiceService;

    @PostMapping
    public ResponseEntity<PracticeResponse> createPractice(
            @Valid @RequestBody PracticeRequest request,
            @AuthenticationPrincipal User user) {
        
        PracticeResponse response = practiceService.createPractice(request, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PracticeResponse>> getUserPractices(@PathVariable UUID userId) {
        return ResponseEntity.ok(practiceService.getPracticesByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePractice(
            @PathVariable UUID id,
            @AuthenticationPrincipal User user) {
        
        practiceService.deletePractice(id, user);
        return ResponseEntity.noContent().build();
    }
}