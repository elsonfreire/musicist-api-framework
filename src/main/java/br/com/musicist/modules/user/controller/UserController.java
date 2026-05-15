package br.com.musicist.modules.user.controller;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.musicist.modules.user.dto.UserUpdateRequest;
import br.com.musicist.modules.user.dto.UserResponse;
import br.com.musicist.modules.user.dto.UserStreakResponse;
import br.com.musicist.modules.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<UserResponse>> getUsers() {
    return ResponseEntity.ok(userService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long id) {
    UserResponse user = userService.findById(id);
    return ResponseEntity.ok(user);
  }

  @GetMapping("/{id}/streak")
  public ResponseEntity<UserStreakResponse> getUserStreak(@PathVariable("id") Long id) {
    return ResponseEntity.ok(userService.getStreak(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserResponse> updateUser(
      @PathVariable("id") Long id, @RequestBody @Valid UserUpdateRequest userDetails) {
    return ResponseEntity.ok(userService.update(id, userDetails));
  }
}
