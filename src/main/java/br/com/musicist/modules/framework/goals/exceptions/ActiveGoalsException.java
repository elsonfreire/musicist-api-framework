package br.com.musicist.modules.framework.goals.exceptions;

import org.springframework.http.HttpStatus;

import br.com.musicist.infra.advice.ApiException;

public class ActiveGoalsException extends ApiException {
  public ActiveGoalsException() {
    super("User still has active pending goals", HttpStatus.CONFLICT);
  }
}
