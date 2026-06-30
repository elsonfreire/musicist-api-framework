package br.com.habit.modules.framework.friendship.exceptions;

import org.springframework.http.HttpStatus;

import br.com.habit.infra.advice.ApiException;

public class FriendshipNotFoundException extends ApiException {
    public FriendshipNotFoundException() {
        super("Friendship not found", HttpStatus.NOT_FOUND);
    }
}
