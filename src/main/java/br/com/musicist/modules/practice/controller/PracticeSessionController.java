package br.com.musicist.modules.practice.controller;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.musicist.modules.framework.user.model.User;
import br.com.musicist.modules.practice.dto.PracticeSessionRequest;
import br.com.musicist.modules.practice.dto.PracticeSessionResponse;
import br.com.musicist.modules.practice.service.PracticeSessionService;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class PracticeSessionController {

  private final PracticeSessionService practiceSessionService;

  @PostMapping
  public ResponseEntity<PracticeSessionResponse> create(
      @RequestBody @Valid PracticeSessionRequest dto, @AuthenticationPrincipal User currentUser) {
    return ResponseEntity.status(201)
        .body(practiceSessionService.createPracticeSession(dto, currentUser));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(
          @PathVariable("id") UUID id, @AuthenticationPrincipal User currentUser) {

    practiceSessionService.deletePracticeSession(id, currentUser);

    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<List<PracticeSessionResponse>> getAllMySessions(
      @AuthenticationPrincipal User currentUser) {
    return ResponseEntity.ok(
        practiceSessionService.getPracticeSessionsByUserId(currentUser.getId()));
  }

  @GetMapping("/{userId}")
  public ResponseEntity<List<PracticeSessionResponse>> getAllUserSessions(
      @PathVariable("userId") UUID userId) {
    return ResponseEntity.ok(practiceSessionService.getPracticeSessionsByUserId(userId));
  }
}
