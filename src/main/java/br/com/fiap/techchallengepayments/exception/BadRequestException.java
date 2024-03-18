package br.com.fiap.techchallengepayments.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseHttpException{

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
