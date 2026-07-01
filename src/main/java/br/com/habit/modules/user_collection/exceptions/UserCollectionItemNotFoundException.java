package br.com.habit.modules.user_collection.exceptions;

import org.springframework.http.HttpStatus;

import br.com.habit.infra.advice.ApiException;

public class UserCollectionItemNotFoundException extends ApiException {
  public UserCollectionItemNotFoundException() {
    super("User collection item not found", HttpStatus.NOT_FOUND);
  }
}
