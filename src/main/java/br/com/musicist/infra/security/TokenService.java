package br.com.musicist.infra.security;

import br.com.musicist.modules.user.model.User;

public interface TokenService {
  public String generateToken(User user);

  public String validateToken(String token);
}
