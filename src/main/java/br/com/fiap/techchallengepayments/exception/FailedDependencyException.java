package br.com.fiap.techchallengepayments.exception;

import org.springframework.http.HttpStatus;

public class FailedDependencyException extends BaseHttpException{

    public FailedDependencyException(String message) {
        super(HttpStatus.FAILED_DEPENDENCY, message);
    }
}

