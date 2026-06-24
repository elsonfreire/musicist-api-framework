package br.com.habit.modules.framework.goals.exceptions;

import org.springframework.http.HttpStatus;

import br.com.habit.infra.advice.ApiException;

public class ActiveGoalsException extends ApiException {
  public ActiveGoalsException() {
    super("User still has active pending goals", HttpStatus.CONFLICT);
  }
}
