package br.com.musicist.modules.framework.auth.exceptions;

import org.springframework.http.HttpStatus;

import br.com.musicist.infra.advice.ApiException;

public class UserNotFoundException extends ApiException {
  public UserNotFoundException() {
    super("User not found", HttpStatus.UNAUTHORIZED);
  }
}
