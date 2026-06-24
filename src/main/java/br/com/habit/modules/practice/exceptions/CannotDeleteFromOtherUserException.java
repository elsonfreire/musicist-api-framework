package br.com.habit.modules.practice.exceptions;

import org.springframework.http.HttpStatus;

import br.com.habit.infra.advice.ApiException;

public class CannotDeleteFromOtherUserException extends ApiException {
  public CannotDeleteFromOtherUserException() {
    super("You cannot delete another user's practice session", HttpStatus.UNAUTHORIZED);
  }
}
