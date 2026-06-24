package br.com.habit.modules.framework.auth.exceptions;

import org.springframework.http.HttpStatus;

import br.com.habit.infra.advice.ApiException;

public class UserAlreadyExistsException extends ApiException {
  public UserAlreadyExistsException(String conflictingField) {
    super("User already exists with this " + conflictingField, HttpStatus.CONFLICT);
  }
}
