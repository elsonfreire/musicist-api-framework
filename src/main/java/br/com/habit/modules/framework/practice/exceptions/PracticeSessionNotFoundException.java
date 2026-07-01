package br.com.habit.modules.framework.practice.exceptions;

import org.springframework.http.HttpStatus;

import br.com.habit.infra.advice.ApiException;

public class PracticeSessionNotFoundException extends ApiException {
  public PracticeSessionNotFoundException() {
    super("Practice session not found", HttpStatus.NOT_FOUND);
  }
}
