package br.com.musicist.modules.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.musicist.infra.security.TokenService;
import br.com.musicist.modules.auth.dto.LoginRequest;
import br.com.musicist.modules.auth.dto.LoginResponse;
import br.com.musicist.modules.auth.dto.RegisterRequest;
import br.com.musicist.modules.auth.dto.RegisterResponse;
import br.com.musicist.modules.auth.exceptions.UserAlreadyExistsException;
import br.com.musicist.modules.auth.exceptions.UserNotFoundException;
import br.com.musicist.modules.auth.exceptions.WrongPasswordException;
import br.com.musicist.modules.user.model.MusicProfile;
import br.com.musicist.modules.user.model.User;
import br.com.musicist.modules.user.repository.MusicProfileRepository;
import br.com.musicist.modules.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;

  private final MusicProfileRepository musicProfileRepository;

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
    
    MusicProfile profile = new MusicProfile();
    profile.setUser(user);

    musicProfileRepository.save(profile);

    user.setMusicProfile(profile);


    return new RegisterResponse(user.getEmail(), user.getUsername());
  }
}
