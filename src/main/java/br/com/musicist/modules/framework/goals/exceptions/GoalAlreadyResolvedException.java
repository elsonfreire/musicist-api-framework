package br.com.musicist.modules.framework.goals.exceptions;

import org.springframework.http.HttpStatus;

import br.com.musicist.infra.advice.ApiException;

public class GoalAlreadyResolvedException extends ApiException {
  public GoalAlreadyResolvedException() {
    super("The goal is already resolved. Its status cannot be changed", HttpStatus.BAD_REQUEST);
  }
}
