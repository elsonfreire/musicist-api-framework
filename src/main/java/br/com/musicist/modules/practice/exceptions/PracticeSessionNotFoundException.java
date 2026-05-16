package br.com.musicist.modules.practice.exceptions;

import org.springframework.http.HttpStatus;

import br.com.musicist.common.exceptions.ApiException;

public class PracticeSessionNotFoundException extends ApiException {
  public PracticeSessionNotFoundException() {
    super("Practice session not found", HttpStatus.NOT_FOUND);
  }
}
