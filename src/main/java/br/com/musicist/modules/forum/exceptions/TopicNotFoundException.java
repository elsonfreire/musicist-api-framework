package br.com.musicist.modules.forum.exceptions;

import org.springframework.http.HttpStatus;

import br.com.musicist.common.exceptions.ApiException;

public class TopicNotFoundException extends ApiException {
  public TopicNotFoundException() {
    super("Topic not found", HttpStatus.NOT_FOUND);
  }
}
