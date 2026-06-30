package br.com.habit.infra.security;

import br.com.habit.modules.framework.user.model.User;

public interface TokenService {
  public String generateToken(User user);

  public String validateToken(String token);
}
