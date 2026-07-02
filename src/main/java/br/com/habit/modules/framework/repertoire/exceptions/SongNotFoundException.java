package br.com.habit.modules.framework.repertoire.exceptions;

import org.springframework.http.HttpStatus;

import br.com.habit.infra.advice.ApiException;

public class SongNotFoundException extends ApiException {
  public SongNotFoundException() {
    super("Song not found", HttpStatus.NOT_FOUND);
  }
}
