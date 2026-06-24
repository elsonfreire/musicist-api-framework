package br.com.musicist.modules.framework.forum.exceptions;

import org.springframework.http.HttpStatus;

import br.com.musicist.infra.advice.ApiException;

public class TopicNotFoundException extends ApiException {
  public TopicNotFoundException() {
    super("Topic not found", HttpStatus.NOT_FOUND);
  }
}
