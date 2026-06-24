package br.com.habit.modules.framework.goals.exceptions;

import org.springframework.http.HttpStatus;

import br.com.habit.infra.advice.ApiException;

public class GeminiUnavailableException extends ApiException {
  public GeminiUnavailableException() {
    super("Gemini service is unavailable", HttpStatus.SERVICE_UNAVAILABLE);
  }
}
