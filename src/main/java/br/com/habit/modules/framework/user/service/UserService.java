package br.com.habit.modules.framework.user.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import br.com.habit.modules.framework.user.dto.UserResponse;
import br.com.habit.modules.framework.user.dto.UserStreakResponse;
import br.com.habit.modules.framework.user.dto.UserUpdateRequest;
import br.com.habit.modules.framework.user.exceptions.UserNotFoundException;
import br.com.habit.modules.framework.user.exceptions.UsernameAlreadyInUseException;
import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.framework.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final DomainProfileUpdater domainProfileUpdater;

    private final UserResponseFactory userResponseFactory;

    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(userResponseFactory::from).toList();
    }

    public UserResponse findById(UUID id) {
        User user = this.findUserEntityById(id);
        return userResponseFactory.from(user);
    }

    public UserResponse update(UUID id, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        if (userUpdateRequest.username() != null) {
            validateUsername(userUpdateRequest.username());
            user.setUsername(userUpdateRequest.username());
        }
        if (userUpdateRequest.bio() != null) user.setBio(userUpdateRequest.bio());
        if (userUpdateRequest.city() != null) user.setCity(userUpdateRequest.city());
        if (userUpdateRequest.state() != null) user.setState(userUpdateRequest.state());

        domainProfileUpdater.update(user, userUpdateRequest.domainProfileData());

        User newUser = userRepository.save(user);

        return userResponseFactory.from(newUser);
    }

    private void validateUsername(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyInUseException();
        }
    }

    public UserStreakResponse getStreak(UUID id) {
        User user = findUserEntityById(id);
        return new UserStreakResponse(user.getCurrentStreak(), user.getLongestStreak());
    }

    public void incrementStreak(UUID id) {
        User user = this.findUserEntityById(id);

        if (user.getLastPracticeDate() != null) {
            LocalDate today = LocalDate.now();
            LocalDate lastPracticeDay = user.getLastPracticeDate().toLocalDate();

            if (today.isEqual(lastPracticeDay)) {
                user.setLastPracticeDate(LocalDateTime.now());
                userRepository.save(user);
                return;
            }
        }

        user.setCurrentStreak(user.getCurrentStreak() + 1);

        if (user.getCurrentStreak() > user.getLongestStreak()) {
            user.setLongestStreak(user.getCurrentStreak());
        }

        user.setLastPracticeDate(LocalDateTime.now());
        userRepository.save(user);
    }

    public void resetStreak(UUID id) {
        User user = this.findUserEntityById(id);

        if (user.getLastPracticeDate() == null) {
            return;
        }

        LocalDate today = LocalDate.now();
        LocalDate lastPracticeDay = user.getLastPracticeDate().toLocalDate();

        if (today.isAfter(lastPracticeDay.plusDays(1))) {
            user.setCurrentStreak(0);
            userRepository.save(user);
        }
    }

    private User findUserEntityById(UUID id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
