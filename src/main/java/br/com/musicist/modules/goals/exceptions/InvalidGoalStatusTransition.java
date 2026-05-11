package br.com.musicist.modules.goals.exceptions;

import org.springframework.http.HttpStatus;

import br.com.musicist.common.exceptions.ApiException;

public class InvalidGoalStatusTransition extends ApiException {
    public InvalidGoalStatusTransition() {
        super("Invalid goal status transition", HttpStatus.BAD_REQUEST);
    }
}
