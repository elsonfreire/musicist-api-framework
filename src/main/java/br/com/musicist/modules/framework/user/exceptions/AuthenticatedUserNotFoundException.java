package br.com.musicist.modules.framework.user.exceptions;

import org.springframework.http.HttpStatus;

import br.com.musicist.infra.advice.ApiException;

public class AuthenticatedUserNotFoundException extends ApiException {
  public AuthenticatedUserNotFoundException() {
    super("Authenticated user not found", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
