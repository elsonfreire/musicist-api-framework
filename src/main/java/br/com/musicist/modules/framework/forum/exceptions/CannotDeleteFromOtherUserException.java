package br.com.musicist.modules.framework.forum.exceptions;

import org.springframework.http.HttpStatus;

import br.com.musicist.infra.advice.ApiException;

public class CannotDeleteFromOtherUserException extends ApiException {
    public CannotDeleteFromOtherUserException() {
        super("You cannot delete another user's topic", HttpStatus.UNAUTHORIZED);
    }
}
