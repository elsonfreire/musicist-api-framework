package br.com.musicist.modules.framework.auth.exceptions;

import org.springframework.http.HttpStatus;

import br.com.musicist.infra.advice.ApiException;

public class WrongPasswordException extends ApiException {
  public WrongPasswordException() {
    super("Wrong password", HttpStatus.UNAUTHORIZED);
  }
}
