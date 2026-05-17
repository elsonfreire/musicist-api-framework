package br.com.musicist.infra.security.exceptions;

import br.com.musicist.common.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class JwtTokenException extends ApiException {
  public JwtTokenException() {
    super("Error while generating token", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
