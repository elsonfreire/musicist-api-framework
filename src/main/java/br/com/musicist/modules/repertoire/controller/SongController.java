package br.com.musicist.modules.repertoire.controller;

import br.com.musicist.modules.framework.user.model.User;
import br.com.musicist.modules.repertoire.dto.SongRequest;
import br.com.musicist.modules.repertoire.dto.UpdateStatusSongRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.musicist.modules.repertoire.dto.SongResponse;
import br.com.musicist.modules.repertoire.enums.LearningStatusType;
import br.com.musicist.modules.repertoire.service.SongService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequestMapping("/repertoire")
@RequiredArgsConstructor
public class SongController {
  private final SongService songService;

  @GetMapping
  public Map<LearningStatusType, List<SongResponse>> getSongsByStatus(
      @AuthenticationPrincipal User currentUser) {
    return songService.findAllByUser(currentUser);
  }

  @PostMapping
  public ResponseEntity<SongResponse> create(
      @RequestBody @Valid SongRequest dto, @AuthenticationPrincipal User currentUser) {
    return ResponseEntity.status(201).body(songService.addSong(dto, currentUser));
  }

  @PatchMapping("/{id}/status")
  public ResponseEntity<SongResponse> updateSongStatus(
      @PathVariable UUID id,
      @RequestBody @Valid UpdateStatusSongRequest dto,
      @AuthenticationPrincipal User currentUser) {
    return ResponseEntity.ok().body(songService.updateSongStatus(id, dto.status(), currentUser));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(
      @PathVariable UUID id, @AuthenticationPrincipal User currentUser) {
    songService.delete(id, currentUser);
    return ResponseEntity.noContent().build();
  }
}
