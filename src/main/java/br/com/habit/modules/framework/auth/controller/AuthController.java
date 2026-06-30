package br.com.habit.modules.framework.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.habit.modules.framework.auth.dto.LoginRequest;
import br.com.habit.modules.framework.auth.dto.LoginResponse;
import br.com.habit.modules.framework.auth.dto.RegisterRequest;
import br.com.habit.modules.framework.auth.dto.RegisterResponse;
import br.com.habit.modules.framework.auth.service.AuthService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequestDto) {
    return ResponseEntity.ok(authService.login(loginRequestDto));
  }

  @PostMapping("/register")
  public ResponseEntity<RegisterResponse> register(
      @RequestBody @Valid RegisterRequest registerRequestDto) {
    return ResponseEntity.status(201).body(authService.register(registerRequestDto));
  }
}
