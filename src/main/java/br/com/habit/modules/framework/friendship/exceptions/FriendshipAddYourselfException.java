package br.com.habit.modules.framework.friendship.exceptions;

import org.springframework.http.HttpStatus;

import br.com.habit.infra.advice.ApiException;

public class FriendshipAddYourselfException extends ApiException {
    public FriendshipAddYourselfException() {
        super("You cannot send a friend request to yourself", HttpStatus.BAD_REQUEST);
    }
}
