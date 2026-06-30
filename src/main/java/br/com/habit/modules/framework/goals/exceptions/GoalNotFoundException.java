package br.com.habit.modules.framework.goals.exceptions;

import org.springframework.http.HttpStatus;

import br.com.habit.infra.advice.ApiException;

public class GoalNotFoundException extends ApiException {
  public GoalNotFoundException() {
    super("Goal not found", HttpStatus.NOT_FOUND);
  }
}
