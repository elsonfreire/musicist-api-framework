package br.com.musicist.modules.framework.goals.exceptions;

import org.springframework.http.HttpStatus;

import br.com.musicist.infra.advice.ApiException;

public class InvalidGoalStatusTransition extends ApiException {
  public InvalidGoalStatusTransition() {
    super("Invalid goal status transition", HttpStatus.BAD_REQUEST);
  }
}
