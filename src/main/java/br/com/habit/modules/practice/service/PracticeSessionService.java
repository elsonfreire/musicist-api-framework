package br.com.habit.modules.practice.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.framework.user.service.UserService;
import br.com.habit.modules.practice.dto.PracticeSessionRequest;
import br.com.habit.modules.practice.dto.PracticeSessionResponse;
import br.com.habit.modules.practice.exceptions.CannotDeleteFromOtherUserException;
import br.com.habit.modules.practice.exceptions.PracticeSessionNotFoundException;
import br.com.habit.modules.practice.model.PracticeSession;
import br.com.habit.modules.practice.repository.PracticeSessionRepository;

@Service
@RequiredArgsConstructor
public class PracticeSessionService {

  private final PracticeSessionRepository practiceSessionRepository;
  private final UserService userService;

  public List<PracticeSessionResponse> getPracticeSessionsByUserId(UUID userId) {
    return practiceSessionRepository.findByUserId(userId).stream()
        .map(PracticeSessionResponse::new)
        .collect(Collectors.toList());
  }

  public PracticeSessionResponse createPracticeSession(
      PracticeSessionRequest requestDto, User user) {
    PracticeSession practiceSession =
        new PracticeSession(
            requestDto.durationMinutes(),
            requestDto.instrument(),
            requestDto.notes(),
            requestDto.date(),
            user);
    PracticeSession savedSession = practiceSessionRepository.save(practiceSession);

    userService.resetStreak(user.getId());
    userService.incrementStreak(user.getId());

    return new PracticeSessionResponse(savedSession);
  }

  public void deletePracticeSession(UUID id, User currentUser) {
    PracticeSession practiceSession =
        practiceSessionRepository.findById(id).orElseThrow(PracticeSessionNotFoundException::new);

    if (!practiceSession.getUser().getId().equals(currentUser.getId())) {
      throw new CannotDeleteFromOtherUserException();
    }
    practiceSessionRepository.delete(practiceSession);
  }
}
