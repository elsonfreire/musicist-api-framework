package br.com.musicist.modules.framework.recommendations.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.musicist.modules.framework.recommendations.dto.RecommendationResponse;
import br.com.musicist.modules.framework.recommendations.service.RecommendationService;
import br.com.musicist.modules.framework.user.model.User;

@RestController
@RequestMapping("/connections")
@RequiredArgsConstructor
public class RecommendationController {

  private final RecommendationService recommendationService;

  @GetMapping("/recommendations")
  public ResponseEntity<List<RecommendationResponse>> getRecommendations(
      @AuthenticationPrincipal User currentUser) {
    return ResponseEntity.ok(recommendationService.getRecommendations(currentUser));
  }
}
