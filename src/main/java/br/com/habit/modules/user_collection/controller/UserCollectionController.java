package br.com.habit.modules.user_collection.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.user_collection.service.UserCollectionService;
import jakarta.validation.Valid;

public abstract class UserCollectionController <TRequest, TResponse, TStatus> {
    protected abstract UserCollectionService<TRequest, TResponse, TStatus> service();

    @GetMapping
    public Map<TStatus, List<TResponse>> list(
        @AuthenticationPrincipal User user) {
        return service().findAllByUser(user);
    }

    @PostMapping
    public ResponseEntity<TResponse> create(
        @RequestBody @Valid TRequest request, @AuthenticationPrincipal User user) {
        return ResponseEntity.status(201).body(service().create(request, user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TResponse> updateStatus(
        @PathVariable UUID id, 
        @RequestBody @Valid TStatus status, 
        @AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(service().updateStatus(id, status, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @PathVariable UUID id, @AuthenticationPrincipal User user) {
        service().delete(id, user);
        return ResponseEntity.noContent().build();
    }
}
