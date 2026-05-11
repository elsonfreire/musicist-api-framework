package br.com.musicist.modules.goals.exceptions;

import br.com.musicist.common.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class ActiveGoalsException extends ApiException {
  public ActiveGoalsException() {
    super("User still has active pending goals", HttpStatus.CONFLICT);
  }
}
