package br.com.musicist.modules.user.exceptions;

import br.com.musicist.common.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class AuthenticatedUserNotFoundException extends ApiException {
  public AuthenticatedUserNotFoundException() {
    super("Authenticated user not found", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
