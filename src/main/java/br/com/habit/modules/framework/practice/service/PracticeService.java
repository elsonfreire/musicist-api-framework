package br.com.habit.modules.framework.practice.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.framework.user.service.UserService;
import br.com.habit.modules.framework.practice.dto.PracticeRequest;
import br.com.habit.modules.framework.practice.dto.PracticeResponse;
import br.com.habit.modules.framework.practice.model.Practice;
import br.com.habit.modules.framework.practice.repository.PracticeRepository;
import br.com.habit.modules.framework.practice.exceptions.CannotDeleteFromOtherUserException;
import br.com.habit.modules.framework.practice.exceptions.PracticeNotFoundException;

@Service
@RequiredArgsConstructor
public class PracticeService {

    private final PracticeRepository practiceRepository;
    private final UserService userService;
    private final List<PracticeStrategy> strategies;

    public List<PracticeResponse> getPracticesByUserId(UUID userId) {
        return practiceRepository.findByUserId(userId).stream()
            .map(PracticeResponse::new)
            .collect(Collectors.toList());
    }

    public PracticeResponse createPractice(PracticeRequest request, User user) {
        PracticeStrategy strategy = strategies.stream()
            .filter(s -> s.getDomainType().equalsIgnoreCase(request.domain()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Unsupported practice domain: " + request.domain()));

        Practice practice = strategy.createPracticeEntity(request, user);
        Practice savedPractice = practiceRepository.save(practice);

        userService.resetStreak(user.getId());
        userService.incrementStreak(user.getId());

        return new PracticeResponse(savedPractice);
    }

    public void deletePractice(UUID id, User currentUser) {
        Practice practice = practiceRepository.findById(id)
            .orElseThrow(PracticeNotFoundException::new);

        if (!practice.getUser().getId().equals(currentUser.getId())) {
            throw new CannotDeleteFromOtherUserException();
        }
        practiceRepository.delete(practice);
    }
}