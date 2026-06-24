package br.com.musicist.modules.framework.goals.exceptions;

import org.springframework.http.HttpStatus;

import br.com.musicist.infra.advice.ApiException;

public class GeminiParseException extends ApiException {
  public GeminiParseException() {
    super("Failed to parse Gemini response", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
