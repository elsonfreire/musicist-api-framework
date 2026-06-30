package br.com.habit.modules.framework.friendship.exceptions;

import org.springframework.http.HttpStatus;

import br.com.habit.infra.advice.ApiException;

public class FriendshipUnauthorizedException extends ApiException {
    public FriendshipUnauthorizedException() {
        super("Only the receiver can accept this request", HttpStatus.FORBIDDEN);
    }
}
