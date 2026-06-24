package br.com.musicist.modules.repertoire.exceptions;

import org.springframework.http.HttpStatus;

import br.com.musicist.infra.advice.ApiException;

public class SongNotFoundException extends ApiException {
  public SongNotFoundException() {
    super("Song not found", HttpStatus.NOT_FOUND);
  }
}
