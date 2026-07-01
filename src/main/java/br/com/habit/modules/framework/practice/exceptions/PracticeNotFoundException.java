package br.com.habit.modules.framework.practice.exceptions;

import org.springframework.http.HttpStatus;

import br.com.habit.infra.advice.ApiException;

public class PracticeNotFoundException extends ApiException {
    public PracticeNotFoundException() {
        super("Practice not found", HttpStatus.NOT_FOUND);
    }
}