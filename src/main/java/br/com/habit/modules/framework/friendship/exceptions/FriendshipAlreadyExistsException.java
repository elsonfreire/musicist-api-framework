package br.com.habit.modules.framework.friendship.exceptions;

import br.com.habit.infra.advice.ApiException;
import br.com.habit.modules.framework.friendship.enums.FriendshipStatusType;

import org.springframework.http.HttpStatus;

public class FriendshipAlreadyExistsException extends ApiException {
    public FriendshipAlreadyExistsException(FriendshipStatusType status) {
        super("Friendship already exists with status: " + status, HttpStatus.CONFLICT);
    }
}
