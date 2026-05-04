package br.com.musicist.modules.friendship.exceptions;

import br.com.musicist.common.exceptions.ApiException;
import br.com.musicist.modules.friendship.enums.FriendshipStatusType;

import org.springframework.http.HttpStatus;

public class FriendshipAlreadyExistsException extends ApiException {
    public FriendshipAlreadyExistsException(FriendshipStatusType status) {
        super("Friendship already exists with status: " + status, HttpStatus.CONFLICT);
    }
}
