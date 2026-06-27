package br.com.habit.modules.framework.user.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import br.com.habit.modules.framework.user.dto.UserResponse;
import br.com.habit.modules.framework.user.dto.UserStreakResponse;
import br.com.habit.modules.framework.user.dto.UserUpdateRequest;
import br.com.habit.modules.framework.user.exceptions.UserNotFoundException;
import br.com.habit.modules.framework.user.exceptions.UsernameAlreadyInUseException;
import br.com.habit.modules.framework.user.model.DomainProfile;
import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.framework.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  
  private final MusicProfileUpdaterStrategy domainProfileUpdater;

  public List<UserResponse> findAll() {
    return userRepository.findAll().stream().map(UserResponse::new).toList();
  }

  public UserResponse findById(UUID id) {
    User user = this.findUserEntityById(id);
    return new UserResponse(user);
  }

  public UserResponse update(UUID id, UserUpdateRequest userUpdated) {
    User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

    if (userUpdated.username() != null) {
      validateUsername(userUpdated.username());
      user.setUsername(userUpdated.username());
    }
    if (userUpdated.bio() != null) user.setBio(userUpdated.bio());
    if (userUpdated.city() != null) user.setCity(userUpdated.city());
    if (userUpdated.state() != null) user.setState(userUpdated.state());

    DomainProfile domainProfile = user.getDomainProfile();
    domainProfileUpdater.update(domainProfile, userUpdated);
    
    User newUser = userRepository.save(user);

    return new UserResponse(newUser);
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
