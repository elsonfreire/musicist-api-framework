package br.com.musicist.modules.goals.exceptions;

import org.springframework.http.HttpStatus;

import br.com.musicist.common.exceptions.ApiException;

public class GoalNotFoundException extends ApiException {
    public GoalNotFoundException() {
        super("Goal not found", HttpStatus.NOT_FOUND);
    }
}
