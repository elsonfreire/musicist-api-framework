package br.com.musicist.modules.goals.exceptions;

import br.com.musicist.common.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class GeminiUnavailableException extends ApiException {
  public GeminiUnavailableException() {
    super("Gemini service is unavailable", HttpStatus.SERVICE_UNAVAILABLE);
  }
}
