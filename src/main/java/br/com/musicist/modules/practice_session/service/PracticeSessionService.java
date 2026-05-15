package br.com.musicist.modules.practice_session.service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import br.com.musicist.modules.practice_session.dto.PracticeSessionRequest;
import br.com.musicist.modules.practice_session.dto.PracticeSessionResponse;
import br.com.musicist.modules.practice_session.exceptions.CannotDeleteFromOtherUserException;
import br.com.musicist.modules.practice_session.exceptions.PracticeSessionNotFoundException;
import br.com.musicist.modules.practice_session.model.PracticeSession;
import br.com.musicist.modules.practice_session.repository.PracticeSessionRepository;
import br.com.musicist.modules.user.model.User;
import br.com.musicist.modules.user.service.UserService;

@Service
@RequiredArgsConstructor
public class PracticeSessionService {

  private final PracticeSessionRepository practiceSessionRepository;
  private final UserService userService;

  public List<PracticeSessionResponse> getPracticeSessionsByUserId(Long userId) {
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

  public void deletePracticeSession(Long id, User currentUser) {
    PracticeSession practiceSession =
        practiceSessionRepository
            .findById(id)
            .orElseThrow(PracticeSessionNotFoundException::new);

    if (!practiceSession.getUser().getId().equals(currentUser.getId())) {
      throw new CannotDeleteFromOtherUserException();
    }
    practiceSessionRepository.delete(practiceSession);
  }
}
