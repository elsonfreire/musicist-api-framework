package br.com.habit.modules.framework.user.exceptions;

import org.springframework.http.HttpStatus;

import br.com.habit.infra.advice.ApiException;

public class UsernameAlreadyInUseException extends ApiException {
  public UsernameAlreadyInUseException() {
    super("Username already in use", HttpStatus.CONFLICT);
  }
}
