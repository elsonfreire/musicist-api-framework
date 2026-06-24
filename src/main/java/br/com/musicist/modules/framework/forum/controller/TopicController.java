package br.com.musicist.modules.framework.forum.controller;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.musicist.modules.framework.forum.dto.CommentRequest;
import br.com.musicist.modules.framework.forum.dto.CommentResponse;
import br.com.musicist.modules.framework.forum.dto.TopicRequest;
import br.com.musicist.modules.framework.forum.dto.TopicResponse;
import br.com.musicist.modules.framework.forum.service.CommentService;
import br.com.musicist.modules.framework.forum.service.TopicService;
import br.com.musicist.modules.framework.user.model.User;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/forum/topics")
@RequiredArgsConstructor
public class TopicController {
  private final TopicService topicService;

  private final CommentService commentService;

  @GetMapping
  public ResponseEntity<List<TopicResponse>> getTopics() {
    return ResponseEntity.ok().body(topicService.findAll());
  }

  @PostMapping
  public ResponseEntity<TopicResponse> create(
      @RequestBody @Valid TopicRequest topicRequest, @AuthenticationPrincipal User currentUser) {
    return ResponseEntity.status(201).body(topicService.create(topicRequest, currentUser));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(
      @PathVariable("id") UUID id, @AuthenticationPrincipal User currentUser) {
    topicService.delete(id, currentUser);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}/comments")
  public ResponseEntity<List<CommentResponse>> getTopicComments(@PathVariable("id") UUID id) {
    return ResponseEntity.ok(commentService.findAllByTopicId(id));
  }

  @PostMapping("/{id}/comments")
  public ResponseEntity<CommentResponse> createTopicComment(
      @PathVariable("id") UUID id,
      @RequestBody @Valid CommentRequest commentRequest,
      @AuthenticationPrincipal User currentUser) {
    return ResponseEntity.status(201).body(commentService.create(commentRequest, id, currentUser));
  }
}
