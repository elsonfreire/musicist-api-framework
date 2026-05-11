package br.com.musicist.modules.goals.exceptions;

import br.com.musicist.common.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class GeminiParseException extends ApiException {
  public GeminiParseException() {
    super("Failed to parse Gemini response", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
