package br.com.fiap.techchallengepayments.exception;

import org.springframework.http.HttpStatus;

public class BaseHttpException extends RuntimeException{

    private HttpStatus statusCode;

    public BaseHttpException(HttpStatus statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
