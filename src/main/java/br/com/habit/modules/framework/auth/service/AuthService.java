package br.com.habit.modules.framework.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.habit.infra.security.TokenService;
import br.com.habit.modules.framework.auth.dto.LoginRequest;
import br.com.habit.modules.framework.auth.dto.LoginResponse;
import br.com.habit.modules.framework.auth.dto.RegisterRequest;
import br.com.habit.modules.framework.auth.dto.RegisterResponse;
import br.com.habit.modules.framework.auth.exceptions.UserAlreadyExistsException;
import br.com.habit.modules.framework.auth.exceptions.UserNotFoundException;
import br.com.habit.modules.framework.auth.exceptions.WrongPasswordException;
import br.com.habit.modules.framework.user.model.DomainProfile;
import br.com.habit.modules.framework.user.model.User;
import br.com.habit.modules.framework.user.repository.UserRepository;
import br.com.habit.modules.framework.user.service.DomainProfileFactory;
import br.com.habit.modules.musicist.profile.MusicProfile;
import br.com.habit.modules.musicist.profile.MusicProfileRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;

  private final DomainProfileFactory domainProfileFactory;

  private final BCryptPasswordEncoder passwordEncoder;

  private final TokenService tokenService;

  public LoginResponse login(LoginRequest loginRequestDto) {
    User user =
        userRepository.findByEmail(loginRequestDto.email()).orElseThrow(UserNotFoundException::new);

    if (!passwordEncoder.matches(loginRequestDto.password(), user.getPasswordHash())) {
      throw new WrongPasswordException();
    }

    var token = tokenService.generateToken(user);

    return new LoginResponse(token);
  }

  public RegisterResponse register(RegisterRequest registerRequestDto) {
    String encryptedPassword = passwordEncoder.encode(registerRequestDto.password());

    if (userRepository.findByEmail(registerRequestDto.email()).isPresent()) {
      throw new UserAlreadyExistsException("email");
    }

    if (userRepository.findByUsername(registerRequestDto.username()).isPresent()) {
      throw new UserAlreadyExistsException("username");
    }

    User newUser =
        new User(registerRequestDto.email(), registerRequestDto.username(), encryptedPassword);
    User user = userRepository.save(newUser);
    
    DomainProfile profile = domainProfileFactory.create(user);
    
    user.setDomainProfile(profile);

    userRepository.save(user);


    return new RegisterResponse(user.getEmail(), user.getUsername());
  }
}
