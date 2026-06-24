package br.com.musicist.modules.framework.friendship.exceptions;

import br.com.musicist.infra.advice.ApiException;
import br.com.musicist.modules.framework.friendship.enums.FriendshipStatusType;

import org.springframework.http.HttpStatus;

public class FriendshipAlreadyExistsException extends ApiException {
    public FriendshipAlreadyExistsException(FriendshipStatusType status) {
        super("Friendship already exists with status: " + status, HttpStatus.CONFLICT);
    }
}
