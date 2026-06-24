package br.com.musicist.modules.framework.user.exceptions;

import org.springframework.http.HttpStatus;

import br.com.musicist.infra.advice.ApiException;

public class UsernameAlreadyInUseException extends ApiException {
  public UsernameAlreadyInUseException() {
    super("Username already in use", HttpStatus.CONFLICT);
  }
}
