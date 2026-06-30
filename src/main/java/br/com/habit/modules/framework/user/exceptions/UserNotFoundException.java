package br.com.habit.modules.framework.user.exceptions;

import org.springframework.http.HttpStatus;

import br.com.habit.infra.advice.ApiException;

public class UserNotFoundException extends ApiException {
  public UserNotFoundException() {
    super("User not found", HttpStatus.NOT_FOUND);
  }
}
