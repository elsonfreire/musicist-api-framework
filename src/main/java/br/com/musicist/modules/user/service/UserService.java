package br.com.musicist.modules.user.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import br.com.musicist.modules.user.dto.UserUpdateRequest;
import br.com.musicist.modules.user.exceptions.UserNotFoundException;
import br.com.musicist.modules.user.exceptions.UsernameAlreadyInUseException;
import br.com.musicist.modules.user.dto.UserResponse;
import br.com.musicist.modules.user.dto.UserStreakResponse;
import br.com.musicist.modules.user.model.User;
import br.com.musicist.modules.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public List<UserResponse> findAll() {
    return userRepository.findAll().stream().map(UserResponse::new).toList();
  }

  public UserResponse findById(Long id) {
    User user = this.findUserEntityById(id);
    return new UserResponse(user);
  }

  public UserResponse update(Long id, UserUpdateRequest userUpdated) {
    User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

    if (userUpdated.username() != null) {
      validateUsername(userUpdated.username());
      user.setUsername(userUpdated.username());
    }

    if (userUpdated.instrument() != null) user.setInstrument(userUpdated.instrument());
    if (userUpdated.bio() != null) user.setBio(userUpdated.bio());
    if (userUpdated.level() != null) user.setLevel(userUpdated.level());
    if (userUpdated.city() != null) user.setCity(userUpdated.city());
    if (userUpdated.state() != null) user.setState(userUpdated.state());
    if (userUpdated.favoriteGenre() != null) user.setFavoriteGenre(userUpdated.favoriteGenre());
    if (userUpdated.interests() != null) {
      user.getInterests().clear();
      user.getInterests().addAll(userUpdated.interests());
    }

    User newUser = userRepository.save(user);

    return new UserResponse(newUser);
  }

  private void validateUsername(String username) {
    if (userRepository.findByUsername(username).isPresent()) {
      throw new UsernameAlreadyInUseException();
    }
  }

  public UserStreakResponse getStreak(Long id) {
    User user = findUserEntityById(id);
    return new UserStreakResponse(user.getCurrentStreak(), user.getLongestStreak());
  }

  public void incrementStreak(Long id) {
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

  public void resetStreak(Long id) {
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

  private User findUserEntityById(Long id) {
    return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
  }
}
