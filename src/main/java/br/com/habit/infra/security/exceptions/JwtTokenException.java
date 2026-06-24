package br.com.habit.infra.security.exceptions;

import org.springframework.http.HttpStatus;

import br.com.habit.infra.advice.ApiException;

public class JwtTokenException extends ApiException {
  public JwtTokenException() {
    super("Error while generating token", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
